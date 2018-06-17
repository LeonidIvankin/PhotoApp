package ru.leonidivankin.photoapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.leonidivankin.photoapp.di.modules.AppModule;
import ru.leonidivankin.photoapp.di.modules.ImageLoaderModule;
import ru.leonidivankin.photoapp.di.modules.RepoModule;
import ru.leonidivankin.photoapp.presenter.MainPresenter;
import ru.leonidivankin.photoapp.view.MainActivity;
import ru.leonidivankin.photoapp.view.PhotoActivity;
import ru.leonidivankin.photoapp.view.RecyclerViewAdapter;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class, ImageLoaderModule.class})
public interface AppComponent {
	void inject(MainActivity mainActivity);
	void inject(MainPresenter mainPresenter);
	void inject(RecyclerViewAdapter recyclerViewAdapter);
	void inject(PhotoActivity photoActivity);
}
