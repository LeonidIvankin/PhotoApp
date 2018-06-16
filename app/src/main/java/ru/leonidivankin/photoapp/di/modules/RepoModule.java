package ru.leonidivankin.photovkapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photovkapp.model.api.ApiService;
import ru.leonidivankin.photovkapp.model.cache.ICache;
import ru.leonidivankin.photovkapp.model.repo.PhotosRepo;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {

	@Provides
	public PhotosRepo photosRepo(ICache cache, ApiService api) {
		return new PhotosRepo(cache, api);
	}
}
