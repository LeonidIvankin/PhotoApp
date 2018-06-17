package ru.leonidivankin.photoapp.view.photoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.leonidivankin.photoapp.R;
import ru.leonidivankin.photoapp.app.App;
import ru.leonidivankin.photoapp.app.Constant;
import ru.leonidivankin.photoapp.model.image.IImageLoader;
import timber.log.Timber;

public class PhotoActivity extends AppCompatActivity {

	@BindView(R.id.text_view_photo_activity) TextView textViewPhotoActivity;
	@BindView(R.id.image_view_photo_activity) ImageView imageViewPhotoActivity;

	@Inject
	IImageLoader<ImageView> imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		ButterKnife.bind(this);
		App.getInstance().getAppComponent().inject(this);


		Intent intent = getIntent();

		String webformatURL = intent.getStringExtra(Constant.SEND_INTENT_FROM_MAINACTIVITY_TO_PHOTOACTIVITY);
		Timber.d(webformatURL);

		imageLoader.loadInto(webformatURL, imageViewPhotoActivity);



	}

}
