package ru.leonidivankin.photoapp.view.photoactivity;

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
import ru.leonidivankin.photoapp.app.Constant;
import ru.leonidivankin.photoapp.model.image.IImageLoader;
import ru.leonidivankin.photoapp.presenter.PhotoPresenter;
import timber.log.Timber;

public class PhotoActivity extends MvpAppCompatActivity implements PhotoView{

	@BindView(R.id.text_view_photo_activity) TextView textViewPhotoActivity;
	@BindView(R.id.image_view_photo_activity) ImageView imageViewPhotoActivity;

	@Inject
	IImageLoader<ImageView> imageLoader;

	@InjectPresenter PhotoPresenter photoPresenter;

	@ProvidePresenter
	public PhotoPresenter createPresenter(){
		PhotoPresenter presenter = new PhotoPresenter(AndroidSchedulers.mainThread());
		App.getInstance().getAppComponent().inject(presenter);

		return presenter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		ButterKnife.bind(this);
		App.getInstance().getAppComponent().inject(this);


		Intent intent = getIntent();
		int position = intent.getIntExtra(Constant.SEND_INTENT_FROM_MAINACTIVITY_TO_PHOTOACTIVITY, 0);
		photoPresenter.loadPhoto(position);


	}


	@Override
	public void showPhoto(String previewURL) {
		imageLoader.loadInto(previewURL, imageViewPhotoActivity);
	}

	@Override
	public void showTag(String tag) {
		textViewPhotoActivity.setText(tag);
	}
}
