package ru.julappdev.githubreps.view.fragments;

import android.support.v4.app.Fragment;

import ru.julappdev.githubreps.presenter.BasePresenter;

/**
 * Created by yulia on 27.04.16.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
