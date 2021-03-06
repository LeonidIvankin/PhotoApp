package ru.leonidivankin.photoapp.view.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.leonidivankin.photoapp.R;
import ru.leonidivankin.photoapp.app.App;
import ru.leonidivankin.photoapp.model.image.IImageLoader;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

	private IListPresenter presenter;
	@Inject
	IImageLoader<ImageView> imageLoader;
	IClickPhoto iClickPhoto;

	public RecyclerViewAdapter(IListPresenter presenter, Context context) {
		this.presenter = presenter;
		iClickPhoto = (IClickPhoto) context;
		//inject для Dagger
		App.getInstance().getAppComponent().inject(this);
	}

	@NonNull
	@Override
	public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
		holder.pos = position;
		presenter.bindView(holder);
		holder.setListener(v -> {
			iClickPhoto.clickPhoto(position);
		});
	}

	@Override
	public int getItemCount() {
		return presenter.getPhotosCount();
	}

	class RecyclerViewHolder extends RecyclerView.ViewHolder implements ListPhotosView {

		@BindView(R.id.text_view_item_tag) TextView textViewItemTag;
		@BindView(R.id.image_view_item_recycler_view) ImageView imageViewItemRecyclerView;
		int pos = 0;

		public RecyclerViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

		}

		//загружаем фото
		@Override
		public void setPreviewUrl(String previewUrl) {
			imageLoader.loadInto(previewUrl, imageViewItemRecyclerView);
		}

		//показываем теги
		@Override
		public void setTags(String tag) {
			textViewItemTag.setText(tag);
		}

		@Override
		public int getPos() {
			return pos;
		}

		public void setListener(View.OnClickListener listener) {
			itemView.setOnClickListener(listener);
		}

	}
}
