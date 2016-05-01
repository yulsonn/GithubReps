package ru.julappdev.githubreps.presenter;

import javax.inject.Inject;

import ru.julappdev.githubreps.common.App;
import ru.julappdev.githubreps.model.Model;
import ru.julappdev.githubreps.model.ModelImpl;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yulia on 27.04.16.
 */
public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
