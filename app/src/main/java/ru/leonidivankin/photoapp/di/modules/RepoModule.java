package ru.leonidivankin.photoapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photoapp.model.api.ApiService;
import ru.leonidivankin.photoapp.model.cache.ICache;
import ru.leonidivankin.photoapp.model.repo.PhotosRepo;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {

	@Provides
	public PhotosRepo photosRepo(ICache cache, ApiService api) {
		return new PhotosRepo(cache, api);
	}
}
