package ru.leonidivankin.photovkapp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.leonidivankin.photovkapp.R;
import ru.leonidivankin.photovkapp.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

	StringsRVAdapter adapter;

	@InjectPresenter MainPresenter presenter;

	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
	}

	@ProvidePresenter
	public MainPresenter provideMainPresenter() {
		return new MainPresenter(AndroidSchedulers.mainThread());
	}

	@Override
	public void init() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new StringsRVAdapter(presenter.getListPresenter());
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void updateList() {
		adapter.notifyDataSetChanged();
	}
}