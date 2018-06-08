package ru.leonidivankin.photovkapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.leonidivankin.photovkapp.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements ListRowView {

	@BindView(R.id.image_view_item_recycler_view)
	ImageView imageView;
	int pos = 0;

	public RecyclerViewHolder(View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}


	@Override
	public void setImageResource(int imageResource) {
		imageView.setImageResource(imageResource);
	}

	@Override
	public int getPos() {
		return pos;
	}
}
