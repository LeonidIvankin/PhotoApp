package ru.leonidivankin.photovkapp.model.repo;

import io.reactivex.Observable;
import ru.leonidivankin.photovkapp.model.api.ApiHolder;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public class PhotosRepo {
	public Observable<Photos> getPhoto(String key){
		return ApiHolder.getApi().getPhotos(key);
	}
}
