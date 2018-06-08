package ru.leonidivankin.photovkapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.leonidivankin.photovkapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

	IImagePresenter presenter;

	public RecyclerViewAdapter(IImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
		return new RecyclerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		holder.pos = position;
		presenter.bindView((ListRowView) holder);
	}

	@Override
	public int getItemCount() {
		return presenter.getImageCount();
	}
}
