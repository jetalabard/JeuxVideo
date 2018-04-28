package fr.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import fr.jeuxvideo.Dialog;

public class TestConnection {
	
	private Activity activity;
	private AlertDialog alertDialog;


	public TestConnection(Activity act) {
		this.activity = act;
	}

	public  boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager 
		= (ConnectivityManager) this.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void testConnectionAndOpenNavigator(final String urlData) {
		if(new TestConnection(activity).isNetworkAvailable())
		{
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+urlData));
			activity.startActivity(browserIntent);
		}
		else{
			showDialog(urlData);
		}

	}

	private void showDialog(final String urlData) {
		Dialog d = new Dialog(activity, "Problème Connexion", "Connectez vous et rééssayez.", true);
		alertDialog  = d.setPositiveButton("Rééssayer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				testConnectionAndOpenNavigator(urlData);
			}
		}).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
			}
		}).create();


		alertDialog.show();
	}
}
