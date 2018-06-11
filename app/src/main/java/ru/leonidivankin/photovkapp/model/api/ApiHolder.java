package ru.leonidivankin.photovkapp.model.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHolder {
	private static ApiHolder instance = new ApiHolder();

	public static ApiHolder getInstance() {
		if (instance == null) {
			instance = new ApiHolder();
		}
		return instance;
	}

	private ApiService api;

	private ApiHolder() {
		Gson gson = new GsonBuilder()
				//.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		//https://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
		api = new Retrofit.Builder()
				.baseUrl("http://pixabay.com")
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build()
				.create(ApiService.class);
	}

	public static ApiService getApi() {
		return getInstance().api;
	}
}
