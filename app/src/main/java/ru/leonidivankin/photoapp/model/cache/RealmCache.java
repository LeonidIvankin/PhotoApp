package ru.leonidivankin.photoapp.model.cache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.leonidivankin.photoapp.app.Constant;
import ru.leonidivankin.photoapp.model.entity.Hits;
import ru.leonidivankin.photoapp.model.entity.Photos;
import ru.leonidivankin.photoapp.model.entity.realm.RealmHits;
import ru.leonidivankin.photoapp.model.entity.realm.RealmPhotos;
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

		realmPhotos = realm
				.where(RealmPhotos.class)
				.equalTo("totalHits", photos.getTotalHits())
				.findFirst();

		RealmPhotos finalRealmPhotos = realmPhotos;

		//удаляем все объекты из realm и записываем новые
		realm.executeTransaction(innerRealm -> {
			finalRealmPhotos.getHits().deleteAllFromRealm();

			for (Hits hit : photos.getHits()) {
				RealmHits realmHits = realm.createObject(RealmHits.class, hit.getPreviewURL());
				realmHits.setTags(hit.getTags());
				realmHits.setWebformatURL(hit.getWebformatURL());
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

			//ищем книгу
			RealmPhotos realmPhotos = realm
					.where(RealmPhotos.class)
					.equalTo("totalHits", "500")
					//FIXME
					.findFirst();

			//если книги не существует, выдаём ошибку
			if (realm == null) {
				Timber.d(Constant.ERROR_GETTING_PHOTOS_FROM_REALM);
			} else {

				//в противном случае записываем из realm в объект photos данные
				Photos photos = new Photos(realmPhotos.getTotalHits());

				List<Hits> hitsList = new ArrayList();

				for (RealmHits realmHits : realmPhotos.getHits()) {
					Hits hits = new Hits();
					hits.setPreviewURL(realmHits.getPreviewURL());
					hits.setTags(realmHits.getTags());
					hits.setWebformatURL(realmHits.getWebformatURL());
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
