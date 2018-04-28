package fr.jeuxvideo.choice_flux_rss;

import java.util.Collections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import fr.jeuxvideo.R;
import fr.metier.Choice;
import model.ChoiceDAO;

public class ChoiceRSSFragment extends Fragment {
	
	public static final String EXTRA_CHOICE_POSITION = "fr.choice.position";
	private static final String CHOICE = "choice";
	public static String EXTRA_FLUX_POSITION = "fr.flux.position";
	private ChoiceRSSAdapter adapter;
	private Choice choice;

	public static Fragment newInstance(int choice_position) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CHOICE_POSITION, choice_position);
		ChoiceRSSFragment fragment = new ChoiceRSSFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(CHOICE, choice);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		if (savedInstanceState != null) {
			choice = (Choice) savedInstanceState.getSerializable(CHOICE);
		} else {
			choice = ChoiceDAO.getInstance(getActivity()).getChoices().get((Integer)getArguments().getSerializable(EXTRA_CHOICE_POSITION));
		}
		getActivity().setTitle(choice.getIntitule());
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.choice_fragment, container, false);
		GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
		Collections.sort(choice.getListFlux());
		adapter =new ChoiceRSSAdapter(getActivity(),choice.getListFlux(),(Integer)getArguments().getSerializable(EXTRA_CHOICE_POSITION));
		gridview.setAdapter(adapter);
		return rootView;
	}
}
