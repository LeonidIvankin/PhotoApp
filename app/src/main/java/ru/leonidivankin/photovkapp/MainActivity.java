package ru.leonidivankin.photovkapp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	@BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
	@BindView(R.id.toolbar) Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
		addFabListener();
	}

	private void addFabListener() {
		setSupportActionBar(toolbar);

		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Добавлено фото", Snackbar.LENGTH_LONG).show();
				applyParams();
			}
		});
	}

	public void applyParams() {
		AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
		params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
	}

}
