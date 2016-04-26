package ru.julappdev.githubreps.view;

import java.util.List;

import ru.julappdev.githubreps.model.data.Repo;

/**
 * Created by yulia on 26.04.16.
 */
public interface IView {

    void showData(List<Repo> list);
    void showError(String error);
    void showEmptyList();
    String getUserName();
}
