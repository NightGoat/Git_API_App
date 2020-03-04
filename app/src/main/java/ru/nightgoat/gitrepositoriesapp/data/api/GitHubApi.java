package ru.nightgoat.gitrepositoriesapp.data.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("/users/{user}/repos")
    Single<List<RepModel>> loadRepos(@Path("user") String user);
}
