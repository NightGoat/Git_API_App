package ru.nightgoat.gitrepositoriesapp.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.nightgoat.gitrepositoriesapp.data.api.RepModel;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void setAdapter(List<RepModel> repos);

    void setUser(RepModel.Owner owner);

    void startProgressBar();
    void stopProgressBar();
}
