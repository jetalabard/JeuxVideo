package fr.jeuxvideo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SimpleFragmentActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragmentManager.findFragmentById(R.id.container) == null) {
			fragmentManager.beginTransaction()
			               .add(R.id.container, createFragment())
			               .commit();
		}
	}

	protected abstract Fragment createFragment();
}
