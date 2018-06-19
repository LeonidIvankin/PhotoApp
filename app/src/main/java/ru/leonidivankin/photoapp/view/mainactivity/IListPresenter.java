package ru.leonidivankin.photoapp.view.mainactivity;

public interface IListPresenter {
	void bindView(ListPhotosView holder);
	int getPhotosCount();
	void onClickPhoto(int position);
}
