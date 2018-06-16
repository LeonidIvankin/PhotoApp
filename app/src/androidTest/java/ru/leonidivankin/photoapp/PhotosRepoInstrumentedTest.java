package ru.leonidivankin.photoapp;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import ru.leonidivankin.photoapp.di.DaggerTestComponent;
import ru.leonidivankin.photoapp.di.TestComponent;
import ru.leonidivankin.photoapp.di.modules.ApiModule;
import ru.leonidivankin.photoapp.model.entity.Photos;
import ru.leonidivankin.photoapp.model.repo.PhotosRepo;

import static junit.framework.Assert.assertEquals;

public class PhotosRepoInstrumentedTest {

	private static MockWebServer mockWebServer;
	@Inject PhotosRepo photosRepo;

	@BeforeClass
	public static void setupClass() throws IOException{
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@AfterClass
	public static void tearDownClass() throws IOException{
		mockWebServer.shutdown();
	}


	@Before
	public void setup(){
		TestComponent component = DaggerTestComponent
				.builder()
				.apiModule(new ApiModule(){
					@Override
					public String baseUrl(){
						return mockWebServer.url("/").toString();
					}
				}).build();

		component.inject(this);
	}

	@Test
	public void getPhoto(){
		mockWebServer.enqueue(createPhotoResponse("url1", "url2"));
		TestObserver<Photos> observer = new TestObserver<>();
		photosRepo.getPhoto().subscribe(observer);

		observer.awaitTerminalEvent();
		observer.assertValueCount(1);
		assertEquals(observer.values().get(0).getTotalHits(), "500");
		assertEquals(observer.values().get(0).getHits().get(0).getPreviewURL(), "url1");
		assertEquals(observer.values().get(0).getHits().get(1).getPreviewURL(), "url2");
	}

	private MockResponse createPhotoResponse(String url1, String url2){
		String body = "{\"totalHits\":500, \"hits\":[{\"previewURL\": \"url1\"}, {\"previewURL\": \"url2\"}]}";

		return new MockResponse().setBody(body);
	}




}
