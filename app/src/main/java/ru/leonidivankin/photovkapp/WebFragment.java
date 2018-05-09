package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WebFragment extends Fragment {

	public static final String KEY = "args";

	public static WebFragment newInstance(Bundle bundle) {
		WebFragment webFragment = new WebFragment();
		Bundle args = new Bundle();
		args.putBundle(KEY, bundle);
		webFragment.setArguments(args);
		return webFragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_web, container, false);
	}

}
