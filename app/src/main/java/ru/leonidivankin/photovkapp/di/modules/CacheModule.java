package ru.leonidivankin.photovkapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photovkapp.model.cache.ICache;
import ru.leonidivankin.photovkapp.model.cache.RealmCache;

@Module
public class CacheModule {

	@Provides
	public ICache cache(){
		return new RealmCache();
	}

}
