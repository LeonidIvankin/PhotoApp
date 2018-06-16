package ru.leonidivankin.photoapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photoapp.model.cache.ImageCache;

@Module
public class ImageCacheModule {

	@Provides
	ImageCache provideImageCache(){
		return new ImageCache();
	}
}
