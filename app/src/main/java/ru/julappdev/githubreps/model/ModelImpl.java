package ru.julappdev.githubreps.model;

import java.util.List;

import ru.julappdev.githubreps.model.api.ApiInterface;
import ru.julappdev.githubreps.model.api.ApiModule;
import ru.julappdev.githubreps.model.dto.BranchDTO;
import ru.julappdev.githubreps.model.dto.ContributorDTO;
import ru.julappdev.githubreps.model.dto.RepositoryDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yulia on 26.04.16.
 */
public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiModule.getApiInterface();

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
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
