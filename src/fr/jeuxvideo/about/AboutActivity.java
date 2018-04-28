package fr.jeuxvideo.about;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import fr.jeuxvideo.MajActivity;
import fr.jeuxvideo.R;
import fr.jeuxvideo.SimpleFragmentActivity;

public class AboutActivity extends SimpleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return AboutFragment.newInstance();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(this) != null) {
				NavUtils.navigateUpFromSameTask(this);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
			return true;
		case R.id.maj:
			Intent intent = new Intent(this, MajActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
