package ru.julappdev.githubreps.model.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yulia on 26.04.16.
 */
public class ApiModule {


    private static final boolean ENABLE_LOG = true;

    private static final boolean ENABLE_AUTH = false;
    private static final String AUTH_64 = "***";

    private static final String BASE_URL = "https://api.github.com/";

    public static ApiInterface getApiInterface() {

        OkHttpClient httpClient = new OkHttpClient();
        OkHttpClient.Builder httBuilder = new OkHttpClient.Builder();

        if (ENABLE_AUTH) {
            httpClient.interceptors().add(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + AUTH_64)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            });
        }

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httBuilder.addInterceptor(interceptor);
        }

        httpClient = httBuilder.build();
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
