package fr.dpapp.buildImage;

import android.app.Activity;
import android.widget.TextView;
import fr.jeuxvideo.R;

public class ImageCategory{

	public TextView draw(Activity act,TextView imageView,String text) {
		imageView.setBackgroundResource(R.drawable.orangecircle);
		imageView.setText(text);
		return imageView;
	}

}
