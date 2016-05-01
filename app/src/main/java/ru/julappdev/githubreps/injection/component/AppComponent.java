package ru.julappdev.githubreps.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.julappdev.githubreps.injection.module.ModelModule;
import ru.julappdev.githubreps.injection.module.PresenterModule;
import ru.julappdev.githubreps.injection.module.ViewModule;
import ru.julappdev.githubreps.model.ModelImpl;
import ru.julappdev.githubreps.presenter.BasePresenter;
import ru.julappdev.githubreps.presenter.RepoInfoPresenter;
import ru.julappdev.githubreps.presenter.RepoListPresenter;
import ru.julappdev.githubreps.view.fragments.RepoInfoFragment;

/**
 * Created by yulia on 01.05.16.
 */
@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

    void inject(RepoInfoFragment repoInfoFragment);
}
