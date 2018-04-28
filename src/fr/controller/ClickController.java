package fr.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import fr.dataloader.DataBase;
import fr.jeuxvideo.SplashScreen;
import fr.jeuxvideo.choice_flux_rss.ChoiceRSSActivity;
import fr.jeuxvideo.choice_flux_rss.ChoiceRSSFragment;
import fr.jeuxvideo.detail.DetailFluxActivity;

public class ClickController implements View.OnClickListener{

	private String urlRedirectButton;
	private int clickMode;
	private Activity activity;
	private int positionItemChoice;
	private String carac;
	private int positionFlux;

	public ClickController(Activity act,String url,int clickMode) {
		this.activity = act;
		this.urlRedirectButton =url;
		this.clickMode = clickMode;
	}
	public ClickController(Activity act,int clickMode,int position, int positionFlux) {
		this.activity = act;
		this.positionItemChoice = position;
		this.positionFlux = positionFlux;
		this.clickMode = clickMode;
	}
	public ClickController(Activity act,int clickMode,int position, String choiceSubtitle) {
		this.activity = act;
		this.positionItemChoice = position;
		this.clickMode = clickMode;
		this.carac = choiceSubtitle;
	}
	public ClickController(Activity act,int clickMode) {
		this.activity = act;
		this.clickMode = clickMode;
	}
	@Override
	public void onClick(View v) {
		//redirection vers une url
		if(this.clickMode == 0)
		{
			Intent i = new Intent(activity, DetailFluxActivity.class);
			i.putExtra(ChoiceRSSFragment.EXTRA_CHOICE_POSITION, positionItemChoice);
			i.putExtra(ChoiceRSSFragment.EXTRA_FLUX_POSITION, positionFlux);
			activity.startActivity(i);
			activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		}
		else if(this.clickMode == 1){
			new TestConnection(activity).testConnectionAndOpenNavigator(urlRedirectButton);
		}
		//redirection vers une simple activité
		else if(this.clickMode == 3){
			if(!carac.isEmpty())
			{
				Toast.makeText(activity, carac, Toast.LENGTH_SHORT).show();
			}
			redirectToChoiceRSSActivity();
		}
		else if(this.clickMode == 4){
			maj();
		}
	}
	private void maj() {
		activity.finish();
		activity.deleteDatabase(DataBase.DATABASE_NAME);
		Intent i = new Intent(activity, SplashScreen.class);
		activity.startActivity(i);
		activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		
	}
	private void redirectToChoiceRSSActivity() {
		Intent i = new Intent(activity, ChoiceRSSActivity.class);
		i.putExtra(ChoiceRSSFragment.EXTRA_CHOICE_POSITION, positionItemChoice);
		activity.startActivity(i);
		activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
}
