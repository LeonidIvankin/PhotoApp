package ru.leonidivankin.photovkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.fab)
	FloatingActionButton floatingActionButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		addFabListener();

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


}
