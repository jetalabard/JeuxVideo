package fr.jeuxvideo.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import fr.controller.ClickController;
import fr.dataloader.AccessData;
import fr.jeuxvideo.R;

public class AboutFragment extends Fragment {
	
	private About about;

	public static Fragment newInstance() {
		AboutFragment aboutFragment = new AboutFragment();
		return aboutFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		getActivity().setTitle(R.string.about);
		about = AccessData.getInstance(getActivity()).loadAbout();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.about_fragment, container, false);
		rootView = createText(about.getIdea(), R.id.textAbout, rootView);
		rootView = createText(about.getWhere(), R.id.intituleData, rootView);
		rootView = createText(about.getData(), R.id.textData, rootView);
		rootView = createButton(about.getUrlData(), about.getUrlData(), R.id.ButtonData, rootView);
		rootView = createButton(about.getFacebook(), about.getFacebook(), R.id.buttonFacebook, rootView);
		rootView = createButton(about.getMess_site(), about.getSite(), R.id.buttonSite, rootView);
		
		return rootView;
	}
	
	private View createButton(String text, String url,int idButton, View roView)
	{
		Button button = (Button) roView.findViewById(idButton);
		button.setText(text);
		button.setOnClickListener(new ClickController(getActivity(),url,1));
		return roView;
	}
	
	private View createText(String text,int idButton, View roView)
	{
		TextView textview = (TextView) roView.findViewById(idButton);
		textview.setText(text);
		return roView;
	}

}
