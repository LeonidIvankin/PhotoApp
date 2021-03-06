package ru.leonidivankin.photoapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.leonidivankin.photoapp.app.App;

@Module
public class AppModule {
	private App app;

	public AppModule(App app) {
		this.app = app;
	}

	@Provides
	public App app() {
		return app;
	}
}
