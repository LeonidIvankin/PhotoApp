package ru.leonidivankin.photoapp.view;

public interface IListPresenter {
	void bindView(ListPhotosView holder);
	int getPhotosCount();
}
