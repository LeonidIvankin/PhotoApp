package ru.leonidivankin.photovkapp.model.cache;

import java.util.List;

import io.reactivex.Observable;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public interface ICache {
	void putPhoto(Photos photos);
	Observable<Photos> getPhoto();

}
