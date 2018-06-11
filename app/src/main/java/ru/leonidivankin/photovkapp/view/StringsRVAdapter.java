package ru.leonidivankin.photovkapp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.leonidivankin.photovkapp.R;

class StringsRVAdapter extends RecyclerView.Adapter<StringsRVAdapter.ViewHolder>{

	IListPresenter presenter;

	public StringsRVAdapter(IListPresenter presenter) {
		this.presenter = presenter;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.pos = position;
		presenter.bindView(holder);
	}

	@Override
	public int getItemCount() {
		return presenter.getStringCount();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements ListRowView{
		@BindView(R.id.tv_title) TextView textView;
		int pos = 0;


		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		@Override
		public void setText(String text) {
			textView.setText(text);
		}

		@Override
		public int getPos() {
			return pos;
		}
	}
}
