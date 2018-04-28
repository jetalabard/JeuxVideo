package fr.jeuxvideo.detail;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.dpapp.buildImage.ImageCategory;
import fr.jeuxvideo.R;
import fr.metier.Item;

public class ItemAdapter extends ArrayAdapter<Item> {

	private Activity act;

	public ItemAdapter(Activity context, List<Item> objects) {
		super(context, 0, objects);
		this.act = context;
	}
	
	private static class ViewHolder {
		TextView itemTitleView;
		TextView itemCategoryView;
		TextView itemDate;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder vh;

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
			vh = new ViewHolder();
			vh.itemTitleView = (TextView) convertView.findViewById(R.id.title);
			vh.itemCategoryView = (TextView) convertView.findViewById(R.id.image);
			vh.itemDate = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		Item item = getItem(position);
		if (item != null) {
			vh.itemTitleView.setText(item.getTitle());
			vh.itemCategoryView = new ImageCategory().draw(act, vh.itemCategoryView, item.getCategory());
			vh.itemDate.setText(item.getPubDate().substring(0, item.getPubDate().length()-5));
		}
		
		return convertView;
	}


}
