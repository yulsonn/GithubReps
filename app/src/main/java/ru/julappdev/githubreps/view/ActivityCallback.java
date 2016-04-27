package ru.julappdev.githubreps.view;

import ru.julappdev.githubreps.presenter.vo.Repository;

/**
 * Created by yulia on 27.04.16.
 */
public interface ActivityCallback {

    void startRepoInfoFragment(Repository repository);
}
