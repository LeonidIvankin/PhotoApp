package ru.leonidivankin.photovkapp.model.repo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.leonidivankin.photovkapp.model.api.ApiHolder;
import ru.leonidivankin.photovkapp.model.entity.Repository;
import ru.leonidivankin.photovkapp.model.entity.User;

public class UsersRepo {
	public Observable<User> getUser(String username){
		return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io());
	}

	public Observable<List<Repository>> getUserRepos (String url){
		return ApiHolder.getApi().getUserRepos(url).subscribeOn(Schedulers.io());
	}
}
