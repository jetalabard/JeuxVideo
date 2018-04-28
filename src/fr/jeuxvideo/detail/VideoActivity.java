package fr.jeuxvideo.detail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;
import fr.jeuxvideo.R;


public class VideoActivity extends Activity{

	private VideoView video;
	private MediaController mediaControls;
	private ProgressDialog progressDialog;
	private int position =0;
	private Uri uri;

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("Position", video.getCurrentPosition());
		video.pause();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		position = savedInstanceState.getInt("Position");
		video.seekTo(position);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String url = getIntent().getExtras().getString(DetailFluxFragment.URL);
		uri = Uri.parse(url);

		setContentView(R.layout.video);
		video = (VideoView) findViewById(R.id.video_view);


		if (mediaControls == null) {
			mediaControls = new MediaController(VideoActivity.this);
		}
		progressDialog = new ProgressDialog(VideoActivity.this);
		progressDialog.setMessage("Chargement...");
		progressDialog.setCancelable(true);
		progressDialog.show();
		try {
			video.setMediaController(mediaControls);
			video.setVideoURI(uri);
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		video.requestFocus();
		video.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mediaPlayer) {
				progressDialog.dismiss();
				video.seekTo(position);
				video.start();
			}
		});
		video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer vmp) {
				VideoActivity.this.finish();           
			}
		});  
	}
}
