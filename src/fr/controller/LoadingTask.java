package fr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.ProgressBar;
import fr.dataloader.DataBase;
import fr.dataloader.URLDownLoader;

public class LoadingTask extends AsyncTask<String, Integer, Integer> {


	private final ProgressBar progressBar;
	private final LoadingTaskFinishedListener finishedListener;
	private Activity activity;
	private WakeLock mWakeLock;
	private OutputStream output;
	private InputStream input;
	private HttpURLConnection connection;

	public interface LoadingTaskFinishedListener {
		void onTaskFinished(); 
	}

	public LoadingTask(ProgressBar progressBar, Activity act,LoadingTaskFinishedListener finishedListener) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;
		this.activity = act;
	}

	@Override
	protected Integer doInBackground(String... params) {
		if(DataBase.isAvailable(activity)==false){
			downloadResources();
		}
		return 1234;
	}


	/**
	 * s'execute avant la tâche asynchrone
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//permet de continuer la tache asynchrone même en veille
		PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
				getClass().getName());
		mWakeLock.acquire();
	}

	/**
	 * downaload file from specific url
	 * create database
	 * delete file download
	 * @param urls
	 */
	private void downloadResources() {
		int count = 10;
		for (int i = 0; i < count; i++) {

			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);
			if(i==0){
				for(String url : URLDownLoader.getUrls()){
					download(url);
				}
				DataBase.getInstance(activity);
				for(String name : URLDownLoader.getNameFileOfUrl()){
					deleteFile(name);
				}
			}
		}
	}

	/**
	 * delete file downlaod
	 * @param urls
	 */
	private void deleteFile(String nameFile) {
		File file = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nameFile);
		file.delete();
	}

	private void download(String param) {

		try{
			int fileLength = createFile(param);
			fillNewFileWithData(fileLength);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAllStream();
		}
	}

	private void closeAllStream() {
		try {
			if (output != null)
				output.close();
			if (input != null)
				input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (connection != null)
			connection.disconnect();
	}

	private void fillNewFileWithData(int fileLength) throws IOException {
		byte data[] = new byte[4096];
		long total = 0;
		int count;
		while ((count = input.read(data)) != -1) {
			if (isCancelled()) {
				input.close();
			}
			total += count;
			if (fileLength > 0) 
				publishProgress((int) (total * 100 / fileLength));
			output.write(data, 0, count);
		}
	}

	private int createFile(String param) throws IOException {
		int fileLength=0;
		URL url;
		url = new URL(param);
		connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			Log.v("download File",  "Server returned HTTP " + connection.getResponseCode()
			+ " " + connection.getResponseMessage());
		}
		fileLength = connection.getContentLength();
		String[] split = param.split("/");
		String name = split[split.length-1];
		String nameFile =  name.substring(0, 10);
		input = connection.getInputStream();
		File folderDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		output= new FileOutputStream(folderDownload.getPath()+"/"+nameFile);
		return fileLength;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]); 
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if(mWakeLock != null){
			mWakeLock.release();
		}
		finishedListener.onTaskFinished(); 
	}
}