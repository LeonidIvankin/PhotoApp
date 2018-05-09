package ru.leonidivankin.photovkapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;

import java.util.ArrayList;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Pair<Fragment, String>> list = new ArrayList<>();

	public CustomFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public void addFragment(Fragment fragment, String tabTitle) {
		this.list.add(new Pair<>(fragment, tabTitle));
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position).first;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return list.get(position).second;
	}
}
