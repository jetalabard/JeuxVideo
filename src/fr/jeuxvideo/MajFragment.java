package fr.jeuxvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import fr.controller.ClickController;
import fr.dataloader.AccessData;
import fr.metier.Maj;

public class MajFragment extends Fragment {

	
	private Maj maj;
	
	public static Fragment newInstance() {
		return new MajFragment();
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		maj = AccessData.getInstance(getActivity()).loadMaj();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.maj_fragment, container, false);
		TextView text = (TextView) rootView.findViewById(R.id.textMaj);
		text.setText(maj.getTextMaj());
		Button b = (Button) rootView.findViewById(R.id.buttonMaj);
		b.setText(maj.getTextButton());
		b.setOnClickListener(new ClickController(getActivity(),4));
		return rootView;
	}
	
}
