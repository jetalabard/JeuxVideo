package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import fr.dataloader.AccessData;
import fr.metier.Choice;

public class ChoiceDAO implements IChoiceDAO {
	
	private Activity act;
	
	private static ChoiceDAO instance =null;
	
	private List<Choice> choices;
	
	private ChoiceDAO(Activity context) {
		this.act = context;
		choices = new ArrayList<Choice>();
	}
	
	public static ChoiceDAO getInstance(Activity context)
	{
		if(instance == null)
			instance = new ChoiceDAO(context);
		return instance;
	}
	/**
	 * retourne la liste des choix
	 */
	@Override
	public List<Choice> getChoices() {
		if(choices.isEmpty())
		{
			try{
				choices.addAll(AccessData.getInstance(act).loadChoices());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Collections.sort(choices);
		}
		return choices;
	}
	
	public Choice getChoice(int position)
	{
		return this.choices.get(position);
	}
	
	

}
