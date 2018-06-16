package ru.leonidivankin.photoapp.model.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.leonidivankin.photoapp.model.entity.Photos;

public interface ApiService {

	@GET("api")
	Observable<Photos> getPhotos(@Query("key") String key);
}
