package ru.julappdev.githubreps.view;

import java.util.List;

import ru.julappdev.githubreps.presenter.vo.Branch;
import ru.julappdev.githubreps.presenter.vo.Contributor;

/**
 * Created by yulia on 27.04.16.
 */
public interface RepoInfoView extends View{

    void showContributors(List<Contributor> contributors);
    void showBranches(List<Branch> branches);
}
