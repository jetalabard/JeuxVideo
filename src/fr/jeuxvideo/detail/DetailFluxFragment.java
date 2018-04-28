package fr.jeuxvideo.detail;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.controller.LoadFluxRSS;
import fr.jeuxvideo.R;
import fr.metier.Choice;
import fr.metier.Flux;
import fr.metier.Item;
import model.ChoiceDAO;

public class DetailFluxFragment extends ListFragment {

	public static final String URL = "fr.url";
	private static int choicePosition;
	private static int fluxPosition;
	private List<Item> items;
	private Flux flux ;
	public static Fragment newInstance(int position, int posFlux) {
		DetailFluxFragment fragment = new DetailFluxFragment();
		choicePosition = position;
		fluxPosition = posFlux;
		return fragment;
	}

	private Callback callbackImpl;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	public interface Callback {
		void onItemSelected(Item item);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Activity a = null;
		if (context instanceof Activity){
			a=(Activity) context;
			if (a instanceof Callback)
				callbackImpl = (Callback) a;
		}

	}

	@Override
	public void onDetach() {
		super.onDetach();
		callbackImpl = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		Choice c = ChoiceDAO.getInstance(getActivity()).getChoice(choicePosition);
		flux =c.getListFlux().get(fluxPosition);
		getActivity().getActionBar().setTitle(flux.getConsole().getName());
		LoadFluxRSS loader = new LoadFluxRSS(getActivity());
		try {
			items = loader.execute(flux.getUrl()).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		setListAdapter(new ItemAdapter(getActivity(),items));
	}

	private void refreshContent(){ 
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				LoadFluxRSS loader = new LoadFluxRSS(getActivity());
				try {
					items = loader.execute(flux.getUrl()).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				setListAdapter(new ItemAdapter(getActivity(),items));
				mSwipeRefreshLayout.setRefreshing(false);
		}
		},100);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.swiperefreshlayout, container, false);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.light_orange);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				refreshContent();				
			}
		});
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (callbackImpl != null) {
			callbackImpl.onItemSelected(((ItemAdapter) getListAdapter()).getItem(position));
		}
	}


	@Override
	public void onResume() {
		super.onResume();
		((ItemAdapter) getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
