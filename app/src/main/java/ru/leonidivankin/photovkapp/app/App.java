package ru.leonidivankin.photovkapp.app;

import android.app.Application;

import io.realm.Realm;
import timber.log.Timber;

public class App extends Application {

	private static App instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Timber.plant(new Timber.DebugTree());
		Realm.init(this);
	}

	public static App getInstance() {
		return instance;
	}
}
