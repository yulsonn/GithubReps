package ru.julappdev.githubreps.presenter;

import ru.julappdev.githubreps.model.Model;
import ru.julappdev.githubreps.model.ModelImpl;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yulia on 27.04.16.
 */
public abstract class BasePresenter implements Presenter {

    protected Model dataRepository = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
