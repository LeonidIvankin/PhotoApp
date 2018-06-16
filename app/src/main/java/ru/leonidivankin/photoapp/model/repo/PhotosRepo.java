package ru.leonidivankin.photovkapp.model.repo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.app.NetworkStatus;
import ru.leonidivankin.photovkapp.model.api.ApiService;
import ru.leonidivankin.photovkapp.model.cache.ICache;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public class PhotosRepo {

	ICache cache;
	ApiService api;

	public PhotosRepo (ICache cache, ApiService api){
		this.cache = cache;
		this.api = api;
	}

	public Observable<Photos> getPhoto(){
		if(NetworkStatus.isOnline()){

			return api
					.getPhotos("9250926-552b631cddef606bad3e807d2")
					.subscribeOn(Schedulers.io())
					.map(photos -> {
						cache.putPhoto(photos);
						return photos;
					});
		} else{
			return cache.getPhoto();
		}

	}
}