package ru.leonidivankin.photovkapp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
	void initRecyclerView();
	void updateRecyclerView();
	void setUserNameText(String username);
	void setIdText(String id);
	void loadImage(String url);
	void updateRepoList();
}
