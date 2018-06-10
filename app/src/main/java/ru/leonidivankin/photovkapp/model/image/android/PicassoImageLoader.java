package ru.leonidivankin.photovkapp.model.image.android;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.leonidivankin.photovkapp.model.image.IImageLoader;

public class PicassoImageLoader implements IImageLoader<ImageView>{
	@Override
	public void loadInto(String url, ImageView container) {
		Picasso.get().load(url).into(container);
	}
}
