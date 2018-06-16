package ru.leonidivankin.photoapp.model.repo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photoapp.app.Constant;
import ru.leonidivankin.photoapp.app.NetworkStatus;
import ru.leonidivankin.photoapp.model.api.ApiService;
import ru.leonidivankin.photoapp.model.cache.ICache;
import ru.leonidivankin.photoapp.model.entity.Photos;

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
					.getPhotos(Constant.TOKEN_KEY)
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
