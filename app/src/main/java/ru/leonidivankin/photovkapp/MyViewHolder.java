package ru.leonidivankin.photovkapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

class MyViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.image_view_item_recycler_view)
	ImageView imageView;

	public MyViewHolder(View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	public void bind(Fruit fruit) {
		imageView.setImageResource(fruit.getImage());
	}
}
