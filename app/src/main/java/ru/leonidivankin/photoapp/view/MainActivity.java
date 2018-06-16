package ru.leonidivankin.photoapp.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.leonidivankin.photoapp.R;
import ru.leonidivankin.photoapp.app.App;
import ru.leonidivankin.photoapp.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

	RecyclerViewAdapter adapter;

	@InjectPresenter MainPresenter presenter;
	@Inject App app;

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	@BindView(R.id.pb_loading) ProgressBar loadingProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
		//inject для Dagger
		App.getInstance().getAppComponent().inject(this);
	}

	@ProvidePresenter
	public MainPresenter provideMainPresenter() {
		MainPresenter presenter = new MainPresenter(AndroidSchedulers.mainThread());
		App.getInstance().getAppComponent().inject(presenter);

		return presenter;
	}

	@Override
	public void initRecyclerView() {
		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		adapter = new RecyclerViewAdapter(presenter.getListPresenter());
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void updateRecyclerView() {
		adapter.notifyDataSetChanged();
	}

	@Override
	public void showLoading() {
		loadingProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		loadingProgressBar.setVisibility(View.GONE);
	}
}