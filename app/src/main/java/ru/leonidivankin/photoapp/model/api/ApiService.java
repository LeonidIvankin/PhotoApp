package ru.leonidivankin.photovkapp.model.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.leonidivankin.photovkapp.model.entity.Photos;

public interface ApiService {

	@GET("api")
	Observable<Photos> getPhotos(@Query("key") String key);
}
