package ru.nightgoat.gitrepositoriesapp.data.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroiftModel {

    private static RetroiftModel INSTANCE = null;
    private GitHubApi API;

    private RetroiftModel() {
        API = createAdaper();
    }

    public static RetroiftModel getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new RetroiftModel();
        }
        return INSTANCE;
    }

    public GitHubApi getAPI(){
        return API;
    }

    private GitHubApi createAdaper() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return adapter.create(GitHubApi.class);
    }
}
