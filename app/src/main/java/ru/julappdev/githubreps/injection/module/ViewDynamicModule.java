package ru.julappdev.githubreps.injection.module;

import dagger.Module;
import dagger.Provides;
import ru.julappdev.githubreps.presenter.RepoListPresenter;
import ru.julappdev.githubreps.view.ActivityCallback;
import ru.julappdev.githubreps.view.RepoListView;

/**
 * Created by yulia on 01.05.16.
 */
@Module
public class ViewDynamicModule {

    private RepoListView view;

    private ActivityCallback activityCallback;

    public ViewDynamicModule(RepoListView view, ActivityCallback activityCallback) {
        this.view = view;
        this.activityCallback = activityCallback;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view, activityCallback);
    }
}
