package ru.leonidivankin.photovkapp.model.image;

public interface IImageLoader<T> {
	void loadInto(String url, T container);
}
