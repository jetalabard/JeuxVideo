package fr.jeuxvideo.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import fr.controller.TestConnection;
import fr.jeuxvideo.Dialog;
import fr.jeuxvideo.R;

public class WebViewActivty extends Activity{

	private WebView webview;
	private ProgressDialog progress;
	private AlertDialog alertDialog;
	private String url;



	protected void onSaveInstanceState(Bundle outState) {
		webview.saveState(outState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		url = getIntent().getExtras().getString(DetailFluxFragment.URL);
		setContentView(R.layout.webview);
		if (savedInstanceState != null){
			webview = (WebView) findViewById(R.id.webview);
			webview.restoreState(savedInstanceState);
		}
		else{
			fillView();
		}
	}

	private void fillView() {
		if(!new TestConnection(this).isNetworkAvailable()){
			showDialogConnection();
		}
		else{
			webview = (WebView) findViewById(R.id.webview);
			WebSettings settings = webview.getSettings();
			settings.setLoadsImagesAutomatically(true);
			settings.setJavaScriptEnabled(true);
			webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
			settings.setUseWideViewPort(true);
			settings.setLoadWithOverviewMode(true);
			settings.setSupportZoom(true);
			settings.setBuiltInZoomControls(true);
			settings.setDisplayZoomControls(false);
			webview.setWebViewClient(new HelloWebViewClient());
			//webview.setWebChromeClient(new WebChromeClient());
			webview.loadUrl(url);
		}
	}

	private void showDialogConnection() {
		Dialog d = new Dialog(this, "Problème Connexion", "Connectez vous et rééssayez.", false);
		alertDialog  = d.setPositiveButton("Rééssayer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				WebViewActivty.this.finish();
				Intent i = new Intent(WebViewActivty.this, WebViewActivty.class);
				i.putExtra(DetailFluxFragment.URL,url);
				startActivity(i);
			}
		}).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
				WebViewActivty.this.finish();
			}
		}).create();
		alertDialog.show();
	}

	private class HelloWebViewClient extends WebViewClient {


		@Override
		public boolean shouldOverrideUrlLoading(WebView webview, String url)
		{
			webview.loadUrl(url);
			return true;
		}
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			progress = new ProgressDialog(WebViewActivty.this);
			progress.setMessage("Chargement...");
			progress.setCancelable(true);
			progress.show();
		}

		public void onPageFinished(WebView view, String url) {
			progress.dismiss();
		}
	}
}
