package ru.julappdev.githubreps.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yulia on 26.04.16.
 */
public class ApiModule {

    private static final boolean ENABLE_AUTH = false;
    private static final String AUTH_64 = "***"; //your code here


    public static ApiInterface getApiInterface() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + AUTH_64)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        }).build();


        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        if (ENABLE_AUTH) builder.client(httpClient);

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
