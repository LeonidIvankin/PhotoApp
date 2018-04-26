package ru.leonidivankin.photovkapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorSchemeActivity extends AppCompatActivity {

	@BindView(R.id.button1)
	Button button1;
	@BindView(R.id.button2)
	Button button2;
	@BindView(R.id.button3)
	Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_scheme);

		ButterKnife.bind(this);

	}
}
