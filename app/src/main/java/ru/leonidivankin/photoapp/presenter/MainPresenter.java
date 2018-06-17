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
import ru.leonidivankin.photoapp.view.IListPresenter;
import ru.leonidivankin.photoapp.view.ListPhotosView;
import ru.leonidivankin.photoapp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>{
	private Scheduler mainThreadScheduler;
	@Inject PhotosRepo photosRepo;
	private ListPresenter listPresenter = new ListPresenter();

	public MainPresenter(Scheduler mainThreadScheduler){
		this.mainThreadScheduler = mainThreadScheduler;
	}

	private class ListPresenter implements IListPresenter {

		private List<Hits> photos = new ArrayList();

		//сетим url и tag для каждой картинки
		@Override
		public void bindView(ListPhotosView holder) {
			holder.setPreviewUrl(photos.get(holder.getPos()).getPreviewURL());
			holder.setTags(photos.get(holder.getPos()).getTags());
		}



		@Override
		public int getPhotosCount() {
			return photos.size();
		}

		@Override
		public void onClick(int position) {
			getViewState().showPhoto(position);
		}
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().initRecyclerView();
		loadPhotos();
	}

	private void loadPhotos() {
		Disposable disposable = photosRepo.getPhoto()
				.observeOn(mainThreadScheduler)
				.subscribe(photo -> {
					//возвращаем объект с распарсеным json
					Timber.d("result in " + Thread.currentThread().getName());
					//передаём все url
					this.listPresenter.photos.addAll(photo.getHits());
					getViewState().updateRecyclerView();
					getViewState().hideLoading();
				}, throwable -> {
					Timber.e(throwable);
					getViewState().hideLoading();

				});
	}

	public ListPresenter getListPresenter(){
		return listPresenter;
	}


}
