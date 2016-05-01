package ru.julappdev.githubreps.presenter;



import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.julappdev.githubreps.common.App;
import ru.julappdev.githubreps.presenter.mappers.RepoListMapper;
import ru.julappdev.githubreps.presenter.vo.Repository;
import ru.julappdev.githubreps.view.ActivityCallback;
import ru.julappdev.githubreps.view.RepoListView;
import rx.Observer;
import rx.Subscription;

/**
 * Created by yulia on 26.04.16.
 */
public class RepoListPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";

    private RepoListView view;

    @Inject
    protected RepoListMapper repoListMapper;

    private List<Repository> repoList;

    private ActivityCallback activityCallback;

    // for DI
    @Inject
    public RepoListPresenter() {
    }

    public RepoListPresenter(RepoListView view, ActivityCallback activityCallback) {
        super();
        App.getComponent().inject(this);
        this.view = view;
        this.activityCallback = activityCallback;
    }

    public void onSearchButtonClick() {
        String name = view.getUserName();
        if (TextUtils.isEmpty(name)) return;

        Subscription subscription = model.getRepoList(name)
                .map(repoListMapper)
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repository> list) {
                        if (list != null && !list.isEmpty()) {
                            repoList = list;
                            view.showRepoList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            repoList = (List<Repository>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }

        if (!isRepoListEmpty()) {
            view.showRepoList(repoList);
        }
    }

    private boolean isRepoListEmpty() {
        return repoList == null || repoList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isRepoListEmpty()) {
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
        }
    }

    public void clickRepo(Repository repository) {
        view.startRepoInfoFragment(repository);
    }

}