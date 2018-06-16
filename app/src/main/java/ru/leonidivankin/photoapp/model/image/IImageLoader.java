package ru.leonidivankin.photoapp.model.image;

public interface IImageLoader<T> {
	void loadInto(String url, T container);
}
