package fr.jeuxvideo.detail;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import fr.jeuxvideo.SimpleFragmentActivity;
import fr.jeuxvideo.choice_flux_rss.ChoiceRSSActivity;
import fr.jeuxvideo.choice_flux_rss.ChoiceRSSFragment;
import fr.metier.Item;

public class DetailFluxActivity extends SimpleFragmentActivity 
implements DetailFluxFragment.Callback {

	private int positionItemChoice;

	@Override
	protected Fragment createFragment() {
		this.positionItemChoice = getIntent().getExtras().getInt(ChoiceRSSFragment.EXTRA_CHOICE_POSITION);
		int posFlux = getIntent().getExtras().getInt(ChoiceRSSFragment.EXTRA_FLUX_POSITION);
		return DetailFluxFragment.newInstance(this.positionItemChoice,posFlux);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == android.R.id.home){
			if (NavUtils.getParentActivityName(this) != null) {
				Intent i = new Intent(this, ChoiceRSSActivity.class);
				i.putExtra(ChoiceRSSFragment.EXTRA_CHOICE_POSITION, positionItemChoice);
				this.startActivity(i);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onItemSelected(Item item) {
		Intent intentWeb = new Intent(this, WebViewActivty.class);
		Intent intentVideo = new Intent(this, VideoActivity.class);
		
		if (item.getEnclosure() != null && !item.getEnclosure().isEmpty()){
			if(!item.getEnclosure().contains(".mp4")){
				intentWeb.putExtra(DetailFluxFragment.URL,item.getEnclosure());
				startActivity(intentWeb);
			}
			else{
				intentVideo.putExtra(DetailFluxFragment.URL,item.getEnclosure());
				startActivity(intentVideo);
			}
		}
		else{
			if(item.getLink() != null && !item.getLink().contains(".mp4")){
				intentWeb.putExtra(DetailFluxFragment.URL,item.getLink());
				startActivity(intentWeb);
			}
			else{
				intentVideo.putExtra(DetailFluxFragment.URL,item.getLink());
				startActivity(intentVideo);
			}
		}
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}
	

}
