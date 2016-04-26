package ru.julappdev.githubreps.presenter;



import java.util.List;

import ru.julappdev.githubreps.model.Model;
import ru.julappdev.githubreps.model.ModelImpl;
import ru.julappdev.githubreps.model.data.Repo;
import ru.julappdev.githubreps.view.IView;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by yulia on 26.04.16.
 */
public class RepoListPresenter implements Presenter {

    private Model model = new ModelImpl();

    private IView view;
    private Subscription subscription = Subscriptions.empty();

    public RepoListPresenter(IView view) {
        this.view = view;
    }

    @Override
    public void onSearchButtonClick() {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getRepoList(view.getUserName())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repo> data) {
                        if (data != null && !data.isEmpty()) {
                            view.showData(data);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
