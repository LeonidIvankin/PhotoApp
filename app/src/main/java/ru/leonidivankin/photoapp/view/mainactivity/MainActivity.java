package ru.leonidivankin.photoapp.view.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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
import ru.leonidivankin.photoapp.app.Constant;
import ru.leonidivankin.photoapp.presenter.MainPresenter;
import ru.leonidivankin.photoapp.view.photoactivity.PhotoActivity;

public class MainActivity extends MvpAppCompatActivity implements MainView {


	private RecyclerViewAdapter adapter;

	@InjectPresenter MainPresenter presenter;
	@Inject App app;

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	@BindView(R.id.progress_bar_loading) ProgressBar loadingProgressBar;
	@BindView(R.id.edit_text_enter_request) EditText editTextEnterRequest;
	@BindView(R.id.toolbar) Toolbar toolbar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		//inject для Dagger
		App.getInstance().getAppComponent().inject(this);

		initListeners();
	}

	private void initListeners() {
		editTextEnterRequest.setOnKeyListener((v, keyCode, event) -> {
			if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
				enterRequest();
				return true;
			}
			return false;
		});
	}

	private void enterRequest() {
		String request = editTextEnterRequest.getText().toString();
		if (!request.isEmpty()) {
			presenter.enterRequest(request);
		}
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

	@Override
	public void sendIntentPosition(int position) {
		Intent intent = new Intent(this, PhotoActivity.class);
		intent.putExtra(Constant.SEND_INTENT_FROM_MAINACTIVITY_TO_PHOTOACTIVITY, position);
		startActivity(intent);

	}


}