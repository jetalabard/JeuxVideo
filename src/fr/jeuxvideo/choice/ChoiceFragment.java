package fr.jeuxvideo.choice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import fr.jeuxvideo.R;
import model.ChoiceDAO;

public class ChoiceFragment extends Fragment {
	
	private ChoiceAdapter adapter;

	public static Fragment newInstance() {
		ChoiceFragment aboutFragment = new ChoiceFragment();
		return aboutFragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.choice_fragment, container, false);
		GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
		adapter =new ChoiceAdapter(getActivity(),ChoiceDAO.getInstance(getActivity()).getChoices());
		gridview.setAdapter(adapter);
		return rootView;
	}
	
}
