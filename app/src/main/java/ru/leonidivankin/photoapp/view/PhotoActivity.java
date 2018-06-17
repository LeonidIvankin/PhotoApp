package ru.leonidivankin.photoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.leonidivankin.photoapp.R;
import ru.leonidivankin.photoapp.app.App;
import ru.leonidivankin.photoapp.model.image.IImageLoader;

public class PhotoActivity extends AppCompatActivity {

	@BindView(R.id.text_view_photo_activity) TextView textViewPhotoActivity;
	@BindView(R.id.image_view_photo_activity) ImageView imageViewPhotoActivity;

	@Inject
	IImageLoader<ImageView> imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		App.getInstance().getAppComponent().inject(this);

		ButterKnife.bind(this);

//https://pixabay.com/get/ea31b70b2ff3063ed1584d05fb1d4f94e677ebd71fac104497f9c37aa0e5b2bc_640.jpg
		Intent intent = getIntent();

		String str = intent.getStringExtra("pos");

		textViewPhotoActivity.setText(str);
		String url = "https://pixabay.com/get/ea31b70b2ff3063ed1584d05fb1d4f94e677ebd71fac104497f9c37aa0e5b2bc_640.jpg";

		imageLoader.loadInto(url, imageViewPhotoActivity);



	}
}
