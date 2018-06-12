package ru.leonidivankin.photovkapp.model.cache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.leonidivankin.photovkapp.model.entity.Hits;
import ru.leonidivankin.photovkapp.model.entity.Photos;
import ru.leonidivankin.photovkapp.model.entity.realm.RealmHits;
import ru.leonidivankin.photovkapp.model.entity.realm.RealmPhotos;
import timber.log.Timber;

public class RealmCache implements ICache {

	//записываем объекты в realm
	@Override
	public void putPhoto(Photos photos) {
		Realm realm = Realm.getDefaultInstance();

		//ищем книгу
		RealmPhotos realmPhotos = realm
				.where(RealmPhotos.class)
				.equalTo("totalHits", photos.getTotalHits())
				.findFirst();
		//если книга не существует, создаём
		if (realmPhotos == null) {
			realm.executeTransaction(innerRealm -> {
				RealmPhotos newRealmPhotos = realm.createObject(RealmPhotos.class, photos.getTotalHits());
			});
		}

		RealmPhotos finalRealmPhotos = realmPhotos;

		realm.executeTransaction(innerRealm -> {
			finalRealmPhotos.getHits().deleteAllFromRealm();

			for (Hits hit : photos.getHits()) {
				RealmHits realmHits = realm.createObject(RealmHits.class, hit.getPreviewURL());
				finalRealmPhotos.getHits().add(realmHits);
			}
		});

		realm.close();

	}

	//получаем объекты из realm
	@Override
	public Observable<Photos> getPhoto() {
		return Observable.create(e -> {
			Realm realm = Realm.getDefaultInstance();

			RealmPhotos realmPhotos = realm
					.where(RealmPhotos.class)
					.equalTo("totalHits", "500")
					//FIXME
					.findFirst();

			if (realm == null) {
				Timber.d("error getting photos from realm");
			} else {
				Photos photos = new Photos(realmPhotos.getTotalHits());

				List<Hits> hitsList = new ArrayList();

				for (RealmHits realmHits : realmPhotos.getHits()) {
					Hits hits = new Hits();
					hits.setPreviewURL(realmHits.getPreviewURL());
					hitsList.add(hits);
				}

				photos.setHits(hitsList);

				e.onNext(photos);
			}
			e.onComplete();
			realm.close();
		});

	}
}
