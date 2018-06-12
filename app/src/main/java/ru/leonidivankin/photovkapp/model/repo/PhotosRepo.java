package ru.leonidivankin.photovkapp.model.repo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.app.NetworkStatus;
import ru.leonidivankin.photovkapp.model.api.ApiHolder;
import ru.leonidivankin.photovkapp.model.cache.ICache;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public class PhotosRepo {

	ICache cache;

	public Observable<Photos> getPhoto(String key){
		if(NetworkStatus.isOnline()){

			return ApiHolder.getApi().getPhotos(key).subscribeOn(Schedulers.io());
		} else{
			return null;
		}

	}
}
