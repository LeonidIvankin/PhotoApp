package ru.leonidivankin.photovkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.leonidivankin.photovkapp.model.entity.Hits;
import ru.leonidivankin.photovkapp.model.repo.PhotosRepo;
import ru.leonidivankin.photovkapp.view.IListPresenter;
import ru.leonidivankin.photovkapp.view.ListRowView;
import ru.leonidivankin.photovkapp.view.MainView;
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

		List<Hits> photos = new ArrayList();


		@Override
		public void bindView(ListRowView holder) {
			holder.setText(photos.get(holder.getPos()).getPreviewURL());
		}

		@Override
		public int getStringCount() {
			return photos.size();
		}
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().init();
		loadStrings();
	}

	private void loadStrings() {
		Disposable disposable = photosRepo.getPhoto()
				.observeOn(mainThreadScheduler)
				.subscribe(photo -> {
					Timber.d("result in " + Thread.currentThread().getName());
					this.listPresenter.photos.addAll(photo.getHits());
					getViewState().updateList();
				}, throwable -> {
					Timber.e(throwable);
				});
	}

	public ListPresenter getListPresenter(){
		return listPresenter;
	}


}
