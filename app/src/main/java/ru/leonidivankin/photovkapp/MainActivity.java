package ru.leonidivankin.photovkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	//@BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.view_pager) ViewPager viewPager;
	@BindView(R.id.tabs) TabLayout tabLayout;

	private MainFragment mainFragment;
	private FavouritesFragment favouritesFragment;
	private CustomFragmentPagerAdapter customFragmentPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		initUI();
	}

	private void initUI() {
		addFabListener();
		initToolbar();
		initFragment();
		initFragmentPageAdapter();
		initViewPager();
		initTabLayout();
	}

	private void addFabListener() {
		floatingActionButton.setOnClickListener(view -> {
			Snackbar.make(view, "Добавлено фото", Snackbar.LENGTH_LONG).show();
			//applyParams();
		});
	}

	private void initToolbar() {
		setSupportActionBar(toolbar);
	}

	private void initFragment() {
		mainFragment = MainFragment.newInstance(null);
		favouritesFragment = FavouritesFragment.newInstance(null);
	}

	private void initFragmentPageAdapter() {
		customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
		customFragmentPagerAdapter.addFragment(mainFragment, "tab1");
		customFragmentPagerAdapter.addFragment(favouritesFragment, "tab2");
	}

	/*public void applyParams() {
		AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
		params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
	}*/

	private void initViewPager() {
		viewPager.setAdapter(customFragmentPagerAdapter);
	}

	private void initTabLayout() {
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				Log.e(TAG, "onTabSelected: " + tab.getText());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				Log.w(TAG, "onTabUnselected: " + tab.getText());
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				Log.i(TAG, "onTabReselected: " + tab.getText());
			}
		});
	}
}