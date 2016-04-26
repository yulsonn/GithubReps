package ru.julappdev.githubreps.model;

import java.util.List;

import ru.julappdev.githubreps.model.api.ApiInterface;
import ru.julappdev.githubreps.model.api.ApiModule;
import ru.julappdev.githubreps.model.data.Repo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yulia on 26.04.16.
 */
public class ModelImpl implements Model {

    ApiInterface apiInterface = ApiModule.getApiInterface();


    @Override
    public Observable<List<Repo>> getRepoList(String name) {
        return apiInterface.getRepositories(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
