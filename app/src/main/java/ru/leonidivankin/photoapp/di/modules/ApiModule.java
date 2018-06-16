package ru.leonidivankin.photovkapp.di.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.leonidivankin.photovkapp.model.api.ApiService;

@Module
public class ApiModule {

	@Singleton
	@Provides
	public ApiService api(Retrofit retrofit){
		return retrofit.create(ApiService.class);
	}

	@Provides
	public Retrofit retrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory,
							 RxJava2CallAdapterFactory rxJava2CallAdapterFactory){

		//https://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
		return new Retrofit.Builder()
				.baseUrl("http://pixabay.com")
				.client(client)
				.addCallAdapterFactory(rxJava2CallAdapterFactory)
				.addConverterFactory(gsonConverterFactory)
				.build();
	}

	@Provides
	public GsonConverterFactory gsonConverterFactory(Gson gson){
		return GsonConverterFactory.create(gson);
	}

	@Provides
	public Gson gson(){
		return new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.create();
	}

	@Provides
	public RxJava2CallAdapterFactory rxJava2CallAdapterFactory(){
		return RxJava2CallAdapterFactory.create();
	}

	//метод для вывода логов в logcat
	@Provides
	public HttpLoggingInterceptor loggingInterceptor(){
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return interceptor;
	}

	@Provides
	public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
		return new OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
				.build();
	}
}