package ru.leonidivankin.photoapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.leonidivankin.photoapp.PhotosRepoInstrumentedTest;
import ru.leonidivankin.photoapp.di.modules.RepoModule;

@Singleton
@Component(modules = {RepoModule.class})
public interface TestComponent {
	void inject(PhotosRepoInstrumentedTest test);
}
