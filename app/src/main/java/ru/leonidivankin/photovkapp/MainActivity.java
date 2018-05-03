package ru.leonidivankin.photovkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	@BindView(R.id.drawer_layout) DrawerLayout drawer;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.nav_view) NavigationView navigationView;
	@BindView(R.id.fab)
	FloatingActionButton floatingActionButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
		addFabListener();

		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);
	}

	private void addFabListener() {
		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickSnackbar(v);
			}
		});
	}

	public void onClickSnackbar(View view) {
		Snackbar.make(view, "Добавлено фото", Snackbar.LENGTH_LONG).show();
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		Intent intent;

		int id = item.getItemId();

		switch (id) {
			case R.id.nav_view_item_selecting_color_scheme:
				intent = new Intent(this, ColorSchemeActivity.class);
				startActivity(intent);
			default:
				break;
		}

		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
