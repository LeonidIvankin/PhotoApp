package ru.leonidivankin.photoapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.leonidivankin.photoapp.model.cache.ICache;
import ru.leonidivankin.photoapp.view.photoactivity.PhotoView;
import timber.log.Timber;

@InjectViewState
public class PhotoPresenter extends MvpPresenter<PhotoView>{
	private Scheduler mainThreadScheduler;

	@Inject ICache cache;

	public PhotoPresenter(Scheduler mainThreadScheduler) {
		this.mainThreadScheduler = mainThreadScheduler;
	}

	public void loadPhoto(int position) {

		Disposable disposable = cache.getPhoto()
				.observeOn(mainThreadScheduler)
				.subscribe(photos -> {
					getViewState().showPhoto(photos.getHits().get(position).getWebformatURL());
				}, throwable -> {
					Timber.e(throwable);
				});


	}
}
