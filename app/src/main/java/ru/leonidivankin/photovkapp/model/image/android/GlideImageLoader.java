package ru.leonidivankin.photovkapp.model.image.android;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.leonidivankin.photovkapp.model.image.IImageLoader;

public class GlideImageLoader implements IImageLoader<ImageView>{
	@Override
	public void loadInto(String url, ImageView container) {
		Glide.with(container.getContext()).load(url).into(container);
	}
}
