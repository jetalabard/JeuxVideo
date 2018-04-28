package fr.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import fr.controller.LoadingTask.LoadingTaskFinishedListener;
import fr.dataloader.DataBase;
import fr.jeuxvideo.Dialog;

public class TestAvailability extends AsyncTask<String, String, String> {
	
	
	private Activity activity;
	private ProgressBar progressBar;
	private LoadingTaskFinishedListener listener;
	private AlertDialog alertDialog;

	public TestAvailability(Activity act, ProgressBar mProgress, LoadingTaskFinishedListener listener) {
		this.activity =act;
		this.progressBar = mProgress;
		this.listener = listener;
	}

	@Override
	protected String doInBackground(String... params) {
		showMessageIfNoConnectionElseLoadApplication();
		return "";
	}
	

	private void showMessageIfNoConnectionElseLoadApplication() {
		TestConnection test = new TestConnection(activity);
		if(!DataBase.isAvailable(activity))
		{
			if(test.isNetworkAvailable()){
				this.progressBar.setVisibility(View.VISIBLE);
				new LoadingTask(progressBar,activity,listener).execute();
			}
			else{
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						showDialogConnection();
					}
				});
			}
		}
		else{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listener.onTaskFinished();
		}
		
	}
	
	private void showDialogConnection() {
		Dialog d = new Dialog(activity, "Problème Connexion", "Connectez vous et rééssayez.", false);
		alertDialog  = d.setPositiveButton("Rééssayer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				showMessageIfNoConnectionElseLoadApplication();
			}
		}).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
				activity.finish();
			}
		}).create();
		alertDialog.show();
	}

}
