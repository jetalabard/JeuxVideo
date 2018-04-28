package fr.jeuxvideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import fr.controller.LoadImageFromAssetFolder;
import fr.controller.LoadingTask.LoadingTaskFinishedListener;
import fr.controller.TestAvailability;
import fr.jeuxvideo.choice.ChoiceActivity;

public class SplashScreen  extends Activity implements LoadingTaskFinishedListener {

	private static ProgressBar mProgress;
	private Animation myAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		ImageView imageLauncher= (ImageView) findViewById(R.id.imageLauncher);
		LoadImageFromAssetFolder.execute(this, imageLauncher, "logo_jvc.png");
		if(savedInstanceState==null){
			myAnimation = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
			
			mProgress = (ProgressBar) findViewById(R.id.progressBar);
			mProgress.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(SplashScreen.this, "Chargement...", Toast.LENGTH_LONG).show();
				}
			});
			imageLauncher.startAnimation(myAnimation);
			new TestAvailability(this,mProgress,this).execute();
		}
		else{
			if(mProgress != null && mProgress.isShown()){
				mProgress.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void onTaskFinished() {
		completeSplash();
	}
	private void completeSplash(){
		startApp();
		finish(); 
	}

	private void startApp() {
		Intent i = new Intent(SplashScreen.this, ChoiceActivity.class);
		startActivity(i);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}



}