package ru.julappdev.githubreps.model.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.julappdev.githubreps.model.data.Repo;
import rx.Observable;

/**
 * Created by yulia on 26.04.16.
 */
public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepositories(@Path("user") String user);
}
