package ru.leonidivankin.photovkapp.view;

public interface IListPresenter {
	void bindView(ListPhotosView holder);
	int getPhotosCount();
}
