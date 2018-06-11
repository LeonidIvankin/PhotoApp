package ru.leonidivankin.photovkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.model.repo.StringsRepo;
import ru.leonidivankin.photovkapp.view.IListPresenter;
import ru.leonidivankin.photovkapp.view.ListRowView;
import ru.leonidivankin.photovkapp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>{
	Scheduler mainThreadScheduler;
	StringsRepo stringsRepo;
	ListPresenter listPresenter = new ListPresenter();

	public MainPresenter(Scheduler mainThreadScheduler){
		this.stringsRepo = new StringsRepo();
		this.mainThreadScheduler = mainThreadScheduler;
	}

	private class ListPresenter implements IListPresenter {

		List<String> strings = new ArrayList();


		@Override
		public void bindView(ListRowView holder) {
			holder.setText(strings.get(holder.getPos()));
		}

		@Override
		public int getStringCount() {
			return strings.size();
		}
	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().init();
		loadStrings();
	}

	private void loadStrings() {
		Disposable disposable = stringsRepo.getData().subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.observeOn(mainThreadScheduler)
				.subscribe(strings -> {
					Timber.d("result in " + Thread.currentThread().getName());
					this.listPresenter.strings.addAll(strings);
					getViewState().updateList();
				}, throwable -> {
					Timber.e(throwable);
				});
	}

	public ListPresenter getListPresenter(){
		return listPresenter;
	}


}
