package ru.leonidivankin.photovkapp.app;

import android.app.Application;

import io.realm.Realm;
import ru.leonidivankin.photovkapp.di.AppComponent;
import ru.leonidivankin.photovkapp.di.DaggerAppComponent;
import ru.leonidivankin.photovkapp.di.modules.AppModule;
import timber.log.Timber;

public class App extends Application {

	private static App instance;
	private AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Timber.plant(new Timber.DebugTree());
		Realm.init(this);

		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	public static App getInstance() {
		return instance;
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}
}
