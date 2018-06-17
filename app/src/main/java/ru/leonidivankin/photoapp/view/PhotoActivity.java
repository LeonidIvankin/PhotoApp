package ru.leonidivankin.photoapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.leonidivankin.photoapp.R;

public class PhotoActivity extends AppCompatActivity {

	@BindView(R.id.text_view_photo_activity) TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		ButterKnife.bind(this);

		Intent intent = getIntent();

		String str = intent.getStringExtra("pos");

		textView.setText(str);



	}
}
