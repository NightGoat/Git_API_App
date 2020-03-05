package ru.nightgoat.gitrepositoriesapp.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import ru.nightgoat.gitrepositoriesapp.data.Model;
import ru.nightgoat.gitrepositoriesapp.data.api.RepModel;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;
    private MainView view;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final String TAG = MainPresenter.class.getName();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new Model();
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
    }

    void getRepos(String user){
        view = getViewState();
        view.startProgressBar();
        disposable.add(model.getRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<RepModel>>() {
            @Override
            public void onSuccess(List<RepModel> repModels) {
                view.setAdapter(repModels);
                view.stopProgressBar();
                if (repModels.size() != 0) getViewState().setUser(repModels.get(0).owner);
            }

            @Override
            public void onError(Throwable e) {
                view.stopProgressBar();
                Log.w(TAG, "onError: " + e.getMessage());
            }
        }));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
