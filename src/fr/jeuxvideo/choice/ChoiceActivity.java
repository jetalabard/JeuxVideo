package fr.jeuxvideo.choice;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import fr.jeuxvideo.MajActivity;
import fr.jeuxvideo.R;
import fr.jeuxvideo.SimpleFragmentActivity;
import fr.jeuxvideo.about.AboutActivity;

public class ChoiceActivity extends SimpleFragmentActivity{

	@Override
	protected Fragment createFragment() {
		return ChoiceFragment.newInstance();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.about)
		{
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			return true;
		}
		else if (id== R.id.maj){
			Intent intent = new Intent(this, MajActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}
}
