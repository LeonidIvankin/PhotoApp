package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {

	public static final String KEY = "args";

	private TextView mTextMessage;
	private FrameLayout frameLayout;
	private DatabaseFragment databaseFragment;
	private WebFragment webFragment;
	private CommonFragment commonFragment;
	private BottomNavigationView navigation;
	private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.id_nbv_database:
					changeFragment(databaseFragment);
					return true;
				case R.id.id_nbv_web:
					changeFragment(webFragment);
					return true;
				case R.id.id_nbv_common:
					changeFragment(commonFragment);
					return true;
				default:
					return false;
			}
		}
	};

	public static MainFragment newInstance(Bundle bundle) {
		MainFragment mainFragment = new MainFragment();
		Bundle args = new Bundle();
		args.putBundle(KEY, bundle);
		mainFragment.setArguments(args);
		return mainFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		initFragment();

		frameLayout = v.findViewById(R.id.containerFragment);
		mTextMessage = v.findViewById(R.id.message);
		navigation = v.findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

		changeFragment(databaseFragment);
		return v;
	}

	private void initFragment() {
		databaseFragment = DatabaseFragment.newInstance(null);
		webFragment = WebFragment.newInstance(null);
		commonFragment = CommonFragment.newInstance(null);
	}

	private void changeFragment(Fragment fragment) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.containerFragment, fragment);
		transaction.commit();
	}

}
