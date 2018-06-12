package ru.leonidivankin.photovkapp.model.repo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.app.NetworkStatus;
import ru.leonidivankin.photovkapp.model.api.ApiHolder;
import ru.leonidivankin.photovkapp.model.cache.ICache;
import ru.leonidivankin.photovkapp.model.cache.RealmCache;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public class PhotosRepo {

	ICache cache;

	public PhotosRepo (){
		cache = new RealmCache();
	}

	public Observable<Photos> getPhoto(){
		if(NetworkStatus.isOnline()){

			return ApiHolder
					.getApi()
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
