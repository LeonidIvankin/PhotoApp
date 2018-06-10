package ru.leonidivankin.photovkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.model.FruitRepo;
import ru.leonidivankin.photovkapp.model.entity.User;
import ru.leonidivankin.photovkapp.model.repo.UsersRepo;
import ru.leonidivankin.photovkapp.view.IImagePresenter;
import ru.leonidivankin.photovkapp.view.IRepoListPresenter;
import ru.leonidivankin.photovkapp.view.ListRowView;
import ru.leonidivankin.photovkapp.view.MainView;
import ru.leonidivankin.photovkapp.view.RepoRowView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements IRepoListPresenter{

	private Scheduler mainThreadScheduler;
	private FruitRepo fruitRepo;
	private ImagePresenter listPresenter = new ImagePresenter();
	private UsersRepo usersRepo;
	private User user;

	public MainPresenter(Scheduler mainThreadScheduler){
		this.fruitRepo = new FruitRepo();
		this.mainThreadScheduler = mainThreadScheduler;
		usersRepo = new UsersRepo();
	}

	@Override
	public void bindRepoListRow(int pos, RepoRowView rowView) {
		if (user != null) {
			rowView.setTitle(user.getRepos().get(pos).getName());
		}
	}

	@Override
	public int getRepoCount() {
		return user == null || user.getRepos() == null ? 0 : user.getRepos().size();
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
		loadData();
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

	private  void loadData(){
		Disposable disposable = usersRepo.getUser("AntonZarytski")
				.subscribe(user -> {
					this.user = user;
					usersRepo.getUserRepos(user.getReposUrl())
							.observeOn(mainThreadScheduler)
							.subscribe(repositories ->{
								this.user.setRepos(repositories);
								getViewState().setUserNameText(user.getLogin());
								getViewState().loadImage(user.getAvatarUrl());
								getViewState().setIdText(user.getId());
								getViewState().updateRepoList();
							}, throwable -> {
								Timber.e("Failed to get user repos");
							});

				}, throwable -> {
					Timber.e("Failed to get name");
				});

	}
}
