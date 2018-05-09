package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavouritesFragment extends Fragment {

	public static final String KEY = "args";

	public static FavouritesFragment newInstance(Bundle bundle) {
		FavouritesFragment favouritesFragment = new FavouritesFragment();
		Bundle args = new Bundle();
		args.putBundle(KEY, bundle);
		favouritesFragment.setArguments(args);
		return favouritesFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_favourites, container, false);
	}

}
