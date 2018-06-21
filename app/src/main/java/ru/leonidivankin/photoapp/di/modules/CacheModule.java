package ru.leonidivankin.photoapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photoapp.model.cache.ICache;
import ru.leonidivankin.photoapp.model.cache.RealmCache;

@Module
public class CacheModule {

	@Provides
	public ICache cache() {
		return new RealmCache();
	}

}
