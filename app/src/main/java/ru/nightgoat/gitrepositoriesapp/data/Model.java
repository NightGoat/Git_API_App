package ru.nightgoat.gitrepositoriesapp.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.nightgoat.gitrepositoriesapp.data.api.RepModel;
import ru.nightgoat.gitrepositoriesapp.data.api.RetroiftModel;

public class Model {

    public Single<List<RepModel>> getRepos(String user){
        return RetroiftModel.getInstance().getAPI().loadRepos(user).subscribeOn(Schedulers.io());
    }

}
