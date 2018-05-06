package ru.leonidivankin.photovkapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class BlankFragment extends Fragment {

	@BindView(R.id.id_recycler_view) RecyclerView recyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_blank, container, false);

		initRecyclerView(v);

		return v;
	}

	private void initRecyclerView(View v) {
		RecyclerView recyclerView = v.findViewById(R.id.id_recycler_view);
		GridLayoutManager layoutManager = new GridLayoutManager(v.getContext(), 2);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		MyAdapter myAdapter = new MyAdapter(Fruit.fruits);
		recyclerView.setAdapter(myAdapter);
	}

	private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

		private Fruit[] fruits;

		public MyAdapter(Fruit[] fruits) {
			this.fruits = fruits;
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
			return new MyViewHolder(cardView);
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.bind(fruits[position]);
		}

		@Override
		public int getItemCount() {
			return fruits.length;
		}
	}

	private class MyViewHolder extends RecyclerView.ViewHolder {
		TextView textView;
		ImageView imageView;

		public MyViewHolder(CardView cardView) {
			super(cardView);
			textView = cardView.findViewById(R.id.text_view);
			imageView = cardView.findViewById(R.id.image_view);
		}

		private void bind(Fruit fruit) {
			textView.setText(fruit.getName());
			imageView.setImageResource(fruit.getImage());
		}
	}
}