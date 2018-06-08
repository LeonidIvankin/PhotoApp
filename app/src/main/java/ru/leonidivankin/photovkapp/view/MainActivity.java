package ru.leonidivankin.photovkapp.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	@InjectPresenter
	MainPresenter presenter;

	private RecyclerViewAdapter recyclerViewAdapter;

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
	public void initRecyclerView() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		recyclerView.setLayoutManager(layoutManager);
		recyclerViewAdapter = new RecyclerViewAdapter(presenter.getListPresenter());
		recyclerView.setAdapter(recyclerViewAdapter);
	}

	@Override
	public void updateRecyclerView() {
		recyclerViewAdapter.notifyDataSetChanged();
	}
}