package ru.leonidivankin.photovkapp.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.leonidivankin.photovkapp.R;
import ru.leonidivankin.photovkapp.model.image.IImageLoader;
import ru.leonidivankin.photovkapp.model.image.android.GlideImageLoader;
import ru.leonidivankin.photovkapp.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	@BindView(R.id.text_view_login) TextView textView;
	@BindView(R.id.text_view_id) TextView textViewId;
	@BindView(R.id.image_view) ImageView imageView;


	@InjectPresenter
	MainPresenter presenter;

	IImageLoader<ImageView> imageLoader;

	private RepoRVAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
		imageLoader = new GlideImageLoader();

	}

	@ProvidePresenter
	public MainPresenter provideMainPresenter() {
		return new MainPresenter(AndroidSchedulers.mainThread());
	}

	@Override
	public void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new RepoRVAdapter(presenter);
		recyclerView.setAdapter(adapter);
	}


	@Override
	public void updateRecyclerView() {
		adapter.notifyDataSetChanged();
	}

	@Override
	public void setUserNameText(String username) {
		textView.setText(username);
	}

	@Override
	public void setIdText(String id) {
		textViewId.setText(id);
	}

	@Override
	public void loadImage(String url) {
		imageLoader.loadInto(url, imageView);
	}

	@Override
	public void updateRepoList() {
		adapter.notifyDataSetChanged();
	}
}