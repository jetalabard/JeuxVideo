package fr.jeuxvideo.choice;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.controller.ClickController;
import fr.controller.LoadImageFromAssetFolder;
import fr.jeuxvideo.R;
import fr.metier.Choice;

public class ChoiceAdapter extends ArrayAdapter<Choice> {

	private Activity activity;

	public ChoiceAdapter(Activity act, List<Choice> objects) {
		super(act, 0, objects);
		this.activity = act;
	}

	private static class ViewHolder {
		TextView choiceTitle;
		ImageView choiceImageView;
		LinearLayout choiceLayout;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh;

		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.choice_adapter, parent,false);
			vh = new ViewHolder();
			vh.choiceLayout = (LinearLayout) convertView.findViewById(R.id.relative1);
			vh.choiceTitle = (TextView) convertView.findViewById(R.id.derniereActu);
			vh.choiceImageView = (ImageView) convertView.findViewById(R.id.imageDerniereActu);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		final Choice choice = getItem(position);
		if (choice != null) {
			vh.choiceTitle.setText(choice.getTitle());
			vh.choiceTitle.setTextSize(15f);
			vh.choiceTitle.setVisibility(View.VISIBLE);
			vh.choiceLayout.setOnClickListener(new ClickController(activity, 3,position,choice.getSubtitle()));
		}
		LoadImageFromAssetFolder.execute(activity, vh.choiceImageView, choice.getImage());
		return convertView;
	}

}
