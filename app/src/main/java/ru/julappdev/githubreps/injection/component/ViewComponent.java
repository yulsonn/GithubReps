package ru.julappdev.githubreps.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.julappdev.githubreps.injection.module.ViewDynamicModule;
import ru.julappdev.githubreps.view.fragments.RepoListFragment;

/**
 * Created by yulia on 01.05.16.
 */
@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(RepoListFragment repoListFragment);
}
