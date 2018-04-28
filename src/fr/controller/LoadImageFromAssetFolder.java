package fr.controller;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class LoadImageFromAssetFolder {
	
	public static void execute(Activity activity,ImageView image,String path)
	{
		AssetManager manager = activity.getAssets();
		InputStream open;
		Bitmap bitmap = null;
		try {
			open = manager.open("image/"+path);
			bitmap = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setImageBitmap(bitmap);
	}
}
