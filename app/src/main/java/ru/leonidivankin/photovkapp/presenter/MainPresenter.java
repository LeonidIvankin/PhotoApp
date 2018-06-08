package ru.leonidivankin.photovkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.model.FruitRepo;
import ru.leonidivankin.photovkapp.view.IImagePresenter;
import ru.leonidivankin.photovkapp.view.ListRowView;
import ru.leonidivankin.photovkapp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>{

	private Scheduler mainThreadScheduler;
	private FruitRepo fruitRepo;
	private ImagePresenter listPresenter = new ImagePresenter();

	public MainPresenter(Scheduler mainThreadScheduler){
		this.fruitRepo = new FruitRepo();
		this.mainThreadScheduler = mainThreadScheduler;
	}


	private class ImagePresenter implements IImagePresenter {
		List<Integer> images = new ArrayList<>();


		@Override
		public void bindView(ListRowView rowView) {
			rowView.setImageResource(images.get(rowView.getPos()));
		}

		@Override
		public int getImageCount() {
			return images.size();
		}
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().initRecyclerView();
		loadStrings();
	}

	private void loadStrings(){
		Disposable disposable = fruitRepo.getData().subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.map(images -> {
					Timber.d("map in " + Thread.currentThread().getName());
					return images;
				})
				.observeOn(mainThreadScheduler)
				.subscribe(images -> {
					Timber.d("result in " + Thread.currentThread().getName());
					this.listPresenter.images.addAll(images);
					getViewState().updateRecyclerView();
				}, throwable -> {
					Timber.e(throwable);
				});
	}

	public ImagePresenter getListPresenter(){
		return listPresenter;
	}
}
