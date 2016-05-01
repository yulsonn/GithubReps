package ru.julappdev.githubreps.presenter.mappers;

import java.util.List;

import javax.inject.Inject;

import ru.julappdev.githubreps.model.dto.RepositoryDTO;
import ru.julappdev.githubreps.presenter.vo.Repository;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by yulia on 26.04.16.
 */
public class RepoListMapper implements Func1<List<RepositoryDTO>, List<Repository>> {

    @Inject
    public RepoListMapper() {
    }

    @Override
    public List<Repository> call(List<RepositoryDTO> repositoryDTOs) {
        if (repositoryDTOs == null) {
            return null;
        }
        List<Repository> repos = Observable.from(repositoryDTOs)
                .map(repositoryDTO -> new Repository(repositoryDTO.getName(), repositoryDTO.getOwner().getLogin()))
                .toList()
                .toBlocking()
                .first();
        return repos;
    }
}
