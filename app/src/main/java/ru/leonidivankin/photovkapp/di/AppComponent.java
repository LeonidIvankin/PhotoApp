package ru.leonidivankin.photovkapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.leonidivankin.photovkapp.di.modules.AppModule;
import ru.leonidivankin.photovkapp.view.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
	void inject(MainActivity mainActivity);
}
