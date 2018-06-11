package ru.leonidivankin.photovkapp.model.repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.leonidivankin.photovkapp.R;
import timber.log.Timber;

public class FruitRepo {

	List<Integer> images;

	public FruitRepo() {
		images = new ArrayList<>();
		images.add(R.drawable.apple);
		images.add(R.drawable.pear);
		images.add(R.drawable.orange);
		images.add(R.drawable.watermelon);
		images.add(R.drawable.cherry);
		images.add(R.drawable.strawberry);
	}

	public Observable<List<Integer>> getData(){
		return Observable.fromCallable(() -> {
			Timber.d("getData in " + Thread.currentThread().getName());
			return images;
		});
	}


}
