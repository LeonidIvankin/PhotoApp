package ru.leonidivankin.photovkapp.model.image.android;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.leonidivankin.photovkapp.model.image.IImageLoader;

public class GlideImageLoader implements IImageLoader<ImageView> {
	@Override
	public void loadInto(String url, ImageView container) {
		//simple glide
		Glide
				.with(container.getContext())
				.load(url)
				.into(container);

		/*GlideApp
				.with(container.getContext())
				.asBitmap().load(url)
				.listener(new RequestListener<Bitmap>() {
					@Override
					public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
						return false;
					}

					@Override
					public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
						return false;
					}
				}).into(container);*/

	}
}
