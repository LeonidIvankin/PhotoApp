package ru.leonidivankin.photovkapp.model.image.android;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ru.leonidivankin.photovkapp.app.NetworkStatus;
import ru.leonidivankin.photovkapp.model.cache.ImageCache;
import ru.leonidivankin.photovkapp.model.image.IImageLoader;
import timber.log.Timber;

public class GlideImageLoader implements IImageLoader<ImageView> {
	@Override
	public void loadInto(String url, ImageView container) {
		//simple glide
		/*Glide
				.with(container.getContext())
				.load(url)
				.into(container);*/

		//если онлайн, берем картинки по url
		if(NetworkStatus.isOnline()){

			GlideApp
					.with(container.getContext())
					.asBitmap().load(url)
					.listener(new RequestListener<Bitmap>() {
						@Override
						public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
							Timber.e(e, "Image load failed");
							return false;
						}

						@Override
						public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
							//сохраняем все картинку
							ImageCache.save(url, resource);

							return false;
						}
					}).into(container);
			//если офлайн, берем картинки из кеша
		} else{
			if(ImageCache.contains(url)){
				GlideApp
						.with(container.getContext())
						.load(ImageCache.getFile(url))
						.into(container);

			}
		}

	}
}
