package ru.julappdev.githubreps.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.julappdev.githubreps.common.App;
import ru.julappdev.githubreps.common.Const;
import ru.julappdev.githubreps.model.api.ApiInterface;
import ru.julappdev.githubreps.model.dto.BranchDTO;
import ru.julappdev.githubreps.model.dto.ContributorDTO;
import ru.julappdev.githubreps.model.dto.RepositoryDTO;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by yulia on 26.04.16.
 */
public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;


    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o)
                .subscribeOn(Schedulers.io())
                .observeOn(uiThread)
                .unsubscribeOn(ioThread) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
        ;
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface
                .getRepositories(name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BranchDTO>> getRepoBranches(String owner, String name) {
        return apiInterface
                .getBranches(owner, name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<ContributorDTO>> getRepoContributors(String owner, String name) {
        return apiInterface
                .getContributors(owner, name)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
