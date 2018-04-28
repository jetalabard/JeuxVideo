package fr.jeuxvideo.choice_flux_rss;

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
import fr.metier.Console;
import fr.metier.Flux;

public class ChoiceRSSAdapter extends ArrayAdapter<Flux> {

	private Activity activity;
	private int choicePosition;

	public ChoiceRSSAdapter(Activity act,List<Flux> list,int positionChoice) {
		super(act,0,list);
		this.activity = act;
		this.choicePosition = positionChoice;
	}

	private static class ViewHolder {
		TextView choiceTitle;
		ImageView choiceImageView;
		LinearLayout choiceLayout;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
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
		fillViewWithItem(position, vh);
		return convertView;
	}

	private void fillViewWithItem(int position, ViewHolder vh) {
		Flux flux = getItem(position);
		if(flux != null){
			Console console = flux.getConsole();
			if (console != null) {
				vh.choiceLayout.getLayoutParams().height = 150;
				vh.choiceLayout.setOnClickListener(new ClickController(activity,0,choicePosition,position));
				if(console.getName().equals("Toutes machines"))
				{
					vh.choiceTitle.setVisibility(View.VISIBLE);
					vh.choiceTitle.setText(console.getName());
				}
				else{
					vh.choiceTitle.setVisibility(View.GONE);
					vh.choiceTitle.setText(null);
				}
				LoadImageFromAssetFolder.execute(activity, vh.choiceImageView, console.getImage());
			}
		}
	}



}
