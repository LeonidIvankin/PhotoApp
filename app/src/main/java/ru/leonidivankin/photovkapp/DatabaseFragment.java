package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DatabaseFragment extends Fragment {

	public static final String KEY = "args";

	public static DatabaseFragment newInstance(Bundle bundle) {
		DatabaseFragment databaseFragment = new DatabaseFragment();
		Bundle args = new Bundle();
		args.putBundle(KEY, bundle);
		databaseFragment.setArguments(args);
		return databaseFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_database, container, false);
	}

}
