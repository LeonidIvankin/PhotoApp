package ru.leonidivankin.photoapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.leonidivankin.photoapp.model.entity.Hits;
import ru.leonidivankin.photoapp.model.repo.PhotosRepo;
import ru.leonidivankin.photoapp.view.mainactivity.IListPresenter;
import ru.leonidivankin.photoapp.view.mainactivity.ListPhotosView;
import ru.leonidivankin.photoapp.view.mainactivity.MainView;
import timber.log.Timber;

import static ru.leonidivankin.photoapp.app.Constant.MAX_LENGTH_TAG;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
	private Scheduler mainThreadScheduler;
	@Inject PhotosRepo photosRepo;
	private ListPresenter listPresenter = new ListPresenter();

	public MainPresenter(Scheduler mainThreadScheduler) {
		this.mainThreadScheduler = mainThreadScheduler;
	}

	private class ListPresenter implements IListPresenter {

		private List<Hits> photos = new ArrayList();

		//сетим url и tag для каждой картинки
		@Override
		public void bindView(ListPhotosView holder) {
			holder.setPreviewUrl(photos.get(holder.getPos()).getPreviewURL());

			String tag = maxLength(photos.get(holder.getPos()).getTags());

			holder.setTags(tag);
		}

		public String maxLength(String str) {
			if (str.length() > MAX_LENGTH_TAG) {
				return str.substring(0, MAX_LENGTH_TAG) + "...";
			}
			return str;
		}


		@Override
		public int getPhotosCount() {
			return photos.size();
		}

	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().initRecyclerView();

		loadPhotos();
	}

	public void enterRequest(String request) {
		loadPhotos(request);
	}

	private void loadPhotos(String request) {
		Disposable disposable = photosRepo.getPhoto(request)
				.observeOn(mainThreadScheduler)
				.subscribe(photo -> {
					//возвращаем объект с распарсеным json
					Timber.d("result in " + Thread.currentThread().getName());
					//передаём все url
					this.listPresenter.photos.clear();
					this.listPresenter.photos.addAll(photo.getHits());
					getViewState().updateRecyclerView();
					getViewState().hideLoading();
				}, throwable -> {
					Timber.e(throwable);
					getViewState().hideLoading();

				});
	}

	private void loadPhotos() {
		loadPhotos("");
	}


	public ListPresenter getListPresenter() {
		return listPresenter;
	}


}
