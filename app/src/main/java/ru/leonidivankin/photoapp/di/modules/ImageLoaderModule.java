package ru.leonidivankin.photoapp.di.modules;

import android.widget.ImageView;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photoapp.model.cache.ImageCache;
import ru.leonidivankin.photoapp.model.image.IImageLoader;
import ru.leonidivankin.photoapp.model.image.android.GlideImageLoader;

@Module(includes = {ImageCacheModule.class})
public class ImageLoaderModule {

	@Provides
	IImageLoader<ImageView> provideImageLoader(ImageCache imageCache) {
		return new GlideImageLoader(imageCache);
	}
}
