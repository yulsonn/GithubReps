package ru.julappdev.githubreps.model;

import java.util.List;

import ru.julappdev.githubreps.model.data.Repo;
import rx.Observable;

/**
 * Created by yulia on 26.04.16.
 */
public interface Model {

    Observable<List<Repo>> getRepoList(String name);
}
