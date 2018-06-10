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

public class RepoRVAdapter extends RecyclerView.Adapter<RepoRVAdapter.ViewHolder> {

	IRepoListPresenter presenter;

	public RepoRVAdapter(IRepoListPresenter presenter) {
		this.presenter = presenter;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		presenter.bindRepoListRow(position, holder);
	}

	@Override
	public int getItemCount() {
		return presenter.getRepoCount();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements RepoRowView{

		@BindView(R.id.text_view_item_recycler_view) TextView textView;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		@Override
		public void setTitle(String title) {
			textView.setText(title);
		}
	}
}
