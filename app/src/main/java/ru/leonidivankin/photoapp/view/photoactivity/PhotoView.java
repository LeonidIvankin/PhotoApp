package ru.leonidivankin.photoapp.view.photoactivity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface PhotoView extends MvpView {

	void showPhoto(String previewURL);

	void showTag(String tag);
}
