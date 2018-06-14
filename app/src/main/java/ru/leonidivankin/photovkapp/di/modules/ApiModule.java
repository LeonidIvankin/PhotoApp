package ru.leonidivankin.photovkapp.di.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.leonidivankin.photovkapp.model.api.ApiService;

@Module
public class ApiModule {

	@Provides
	public ApiService api(){
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.create();

		//https://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
		return new Retrofit.Builder()
				.baseUrl("http://pixabay.com")
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build()
				.create(ApiService.class);
	}
}
