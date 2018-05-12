package ru.leonidivankin.photovkapp;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class BlankFragment extends Fragment {

	public static final String TAG = BlankFragment.class.getSimpleName();
	@BindView(R.id.id_recycler_view) RecyclerView recyclerView;
	private int defaultColorImageViewStar;
	private int colorImageViewStar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_blank, container, false);
		initColorStar();

		initRecyclerView(v);

		return v;
	}

	private void initColorStar() {
		defaultColorImageViewStar = getResources().getColor(R.color.color_star_black);
		colorImageViewStar = getResources().getColor(R.color.colorAccent);
	}


	private void initRecyclerView(View v) {
		RecyclerView recyclerView = v.findViewById(R.id.id_recycler_view);
		GridLayoutManager layoutManager = new GridLayoutManager(v.getContext(), 2);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(Fruit.fruits);
		recyclerView.setAdapter(recyclerViewAdapter);
	}

	private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {

		private Fruit[] fruits;

		public RecyclerViewAdapter(Fruit[] fruits) {
			this.fruits = fruits;
		}

		@Override
		public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
			return new RecyclerViewViewHolder(cardView);
		}

		@Override
		public void onBindViewHolder(RecyclerViewViewHolder holder, int position) {
			holder.bind(fruits[position]);
			holder.setListener(v -> {
				applyEditImageViewColor(holder.imageViewStar);
			});
		}

		//установка цвета звёздочке
		private void applyEditImageViewColor(ImageView imageViewStar) {
			int color = imageViewStar.getImageTintList().getDefaultColor();
			Log.d(TAG, String.valueOf(color));

			if (defaultColorImageViewStar != color) {
				imageViewStar.setImageTintList(ColorStateList.valueOf(defaultColorImageViewStar));
				Snackbar.make(imageViewStar, "Фото удалено из \"Понравившиеся\"", Snackbar.LENGTH_LONG).show();
			} else {
				imageViewStar.setImageTintList(ColorStateList.valueOf(colorImageViewStar));
				Snackbar.make(imageViewStar, "Фото добавлено в \"Понравившиеся\"", Snackbar.LENGTH_LONG).show();
			}

		}

		@Override
		public int getItemCount() {
			return fruits.length;
		}
	}

	private class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
		TextView textView;
		ImageView imageViewPhoto;
		ImageView imageViewStar;

		public RecyclerViewViewHolder(CardView cardView) {
			super(cardView);
			textView = cardView.findViewById(R.id.id_text_view);
			imageViewPhoto = cardView.findViewById(R.id.id_image_view_photo);
			imageViewStar = cardView.findViewById(R.id.id_image_view_star);//присвоить звёздочке дефолтный цвет

		}

		private void bind(Fruit fruit) {
			textView.setText(fruit.getName());
			imageViewPhoto.setImageResource(fruit.getImage());
			imageViewStar.setImageTintList(ColorStateList.valueOf(defaultColorImageViewStar));
		}

		private void setListener(View.OnClickListener listener) {
			imageViewStar.setOnClickListener(listener);
		}
	}
}