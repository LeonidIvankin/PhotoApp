package ru.leonidivankin.photovkapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

	Fruit[] fruits;

	public MyAdapter(Fruit[] fruits) {
		this.fruits = fruits;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
		return new MyViewHolder(view);
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
