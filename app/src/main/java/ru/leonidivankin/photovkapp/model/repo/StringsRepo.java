package ru.leonidivankin.photovkapp.model.repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import timber.log.Timber;

public class StringsRepo {
	List<String> strings;

	public StringsRepo() {
		strings = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			strings.add("string" + i);
		}
	}

	public Observable<List<String>> getData(){
		return Observable.fromCallable(() ->{
			Timber.d("getData in " + Thread.currentThread().getName());
			return strings;
		});
	}


}