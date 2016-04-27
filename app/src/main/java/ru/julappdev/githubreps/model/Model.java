package ru.julappdev.githubreps.model;

import java.util.List;

import ru.julappdev.githubreps.model.dto.BranchDTO;
import ru.julappdev.githubreps.model.dto.ContributorDTO;
import ru.julappdev.githubreps.model.dto.RepositoryDTO;
import rx.Observable;

/**
 * Created by yulia on 26.04.16.
 */
public interface Model {

    Observable<List<RepositoryDTO>> getRepoList(String name);
    Observable<List<BranchDTO>> getRepoBranches(String owner, String name);
    Observable<List<ContributorDTO>> getRepoContributors(String owner, String name);
}
