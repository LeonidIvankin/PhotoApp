package ru.leonidivankin.photoapp.model.cache;

import io.reactivex.Observable;
import ru.leonidivankin.photoapp.model.entity.Photos;

public interface ICache {
	void putPhoto(Photos photos);

	Observable<Photos> getPhoto();

}
