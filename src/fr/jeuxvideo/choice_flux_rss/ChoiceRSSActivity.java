package fr.jeuxvideo.choice_flux_rss;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import fr.jeuxvideo.SimpleFragmentActivity;

public class ChoiceRSSActivity extends SimpleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return ChoiceRSSFragment.newInstance(
				getIntent().getExtras().getInt(ChoiceRSSFragment.EXTRA_CHOICE_POSITION));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == android.R.id.home){
			if (NavUtils.getParentActivityName(this) != null) {
				NavUtils.navigateUpFromSameTask(this);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}
}
