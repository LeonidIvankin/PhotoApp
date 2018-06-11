package ru.leonidivankin.photovkapp.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import ru.leonidivankin.photovkapp.model.entity.Photos;
import ru.leonidivankin.photovkapp.model.entity.Repository;
import ru.leonidivankin.photovkapp.model.entity.User;

public interface ApiService {
	@GET("/users/{user}")
	Observable<User> getUser(@Path("user") String username);

	@GET
	Observable<List<Repository>> getUserRepos(@Url String url);

	@GET("api")
	Observable<Photos> getPhotos(@Query("key") String key);
}
