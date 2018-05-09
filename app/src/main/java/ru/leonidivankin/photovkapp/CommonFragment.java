package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommonFragment extends Fragment {

	public static final String KEY = "args";

	public static CommonFragment newInstance(Bundle bundle) {
		CommonFragment commonFragment = new CommonFragment();
		Bundle args = new Bundle();
		args.putBundle(KEY, bundle);
		commonFragment.setArguments(args);
		return commonFragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_common, container, false);
	}

}
