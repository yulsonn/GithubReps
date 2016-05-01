package ru.julappdev.githubreps.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.julappdev.githubreps.model.Model;
import ru.julappdev.githubreps.model.ModelImpl;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yulia on 01.05.16.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
