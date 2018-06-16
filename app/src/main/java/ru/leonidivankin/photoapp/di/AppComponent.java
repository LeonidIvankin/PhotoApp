package ru.leonidivankin.photovkapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.leonidivankin.photovkapp.di.modules.AppModule;
import ru.leonidivankin.photovkapp.di.modules.RepoModule;
import ru.leonidivankin.photovkapp.presenter.MainPresenter;
import ru.leonidivankin.photovkapp.view.MainActivity;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class})
public interface AppComponent {
	void inject(MainActivity mainActivity);
	void inject(MainPresenter mainPresenter);
}
