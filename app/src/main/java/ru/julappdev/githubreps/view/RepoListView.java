package ru.julappdev.githubreps.view;

import java.util.List;

import ru.julappdev.githubreps.presenter.vo.Repository;

/**
 * Created by yulia on 27.04.16.
 */
public interface RepoListView extends View {

    void showRepoList(List<Repository> vo);
    void startRepoInfoFragment(Repository vo);
    void showEmptyList();
    String getUserName();
}
