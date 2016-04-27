package ru.julappdev.githubreps.model.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.julappdev.githubreps.model.dto.BranchDTO;
import ru.julappdev.githubreps.model.dto.ContributorDTO;
import ru.julappdev.githubreps.model.dto.RepositoryDTO;
import rx.Observable;

/**
 * Created by yulia on 26.04.16.
 */
public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<RepositoryDTO>> getRepositories(@Path("user") String user);

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<ContributorDTO>> getContributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/branches")
    Observable<List<BranchDTO>> getBranches(@Path("owner") String owner, @Path("repo") String repo);
}
