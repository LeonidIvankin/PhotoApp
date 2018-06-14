package ru.leonidivankin.photovkapp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.leonidivankin.photovkapp.R;
import ru.leonidivankin.photovkapp.model.image.IImageLoader;
import ru.leonidivankin.photovkapp.model.image.android.GlideImageLoader;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

	IListPresenter presenter;

	public RecyclerViewAdapter(IListPresenter presenter) {
		this.presenter = presenter;
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
	}

	@Override
	public int getItemCount() {
		return presenter.getPhotosCount();
	}

	class RecyclerViewHolder extends RecyclerView.ViewHolder implements ListPhotosView {
		@BindView(R.id.text_view_item_tag) TextView textViewItemTag;
		@BindView(R.id.image_view_item_recycler_view) ImageView imageViewItemRecyclerView;
		int pos = 0;
		IImageLoader<ImageView> imageLoader;


		public RecyclerViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			imageLoader = new GlideImageLoader();
		}

		@Override
		public void setPreviewUrl(String previewUrl) {
			imageLoader.loadInto(previewUrl, imageViewItemRecyclerView);
		}

		@Override
		public void setTags(String tag) {
			textViewItemTag.setText(tag);
		}

		@Override
		public int getPos() {
			return pos;
		}
	}
}
