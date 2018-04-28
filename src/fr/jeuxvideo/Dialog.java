package fr.jeuxvideo;

import android.app.AlertDialog;
import android.content.Context;

public class Dialog extends AlertDialog.Builder{

	public Dialog(Context context,String title,String message,boolean cancelable) {
		super(context);
		this.setMessage(message);
		this.setTitle(title);
		this.setCancelable(cancelable);
		
	}

}
