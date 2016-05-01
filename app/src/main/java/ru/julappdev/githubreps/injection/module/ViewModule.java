package ru.julappdev.githubreps.injection.module;

import dagger.Module;
import dagger.Provides;
import ru.julappdev.githubreps.presenter.RepoInfoPresenter;

/**
 * Created by yulia on 01.05.16.
 */
@Module
public class ViewModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }
}
