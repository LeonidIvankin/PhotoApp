package ru.leonidivankin.photoapp.view.mainactivity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
	void initRecyclerView();

	void updateRecyclerView();

	void showLoading();

	void hideLoading();

}
