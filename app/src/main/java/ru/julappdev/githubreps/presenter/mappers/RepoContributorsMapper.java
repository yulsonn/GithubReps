package ru.julappdev.githubreps.presenter.mappers;

import java.util.List;

import ru.julappdev.githubreps.model.dto.ContributorDTO;
import ru.julappdev.githubreps.presenter.vo.Contributor;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by yulia on 26.04.16.
 */
public class RepoContributorsMapper implements Func1<List<ContributorDTO>, List<Contributor>> {

    @Override
    public List<Contributor> call(List<ContributorDTO> contributorDTOs) {
        List<Contributor> contributors = Observable.from(contributorDTOs)
                .map(contributorDTO -> new Contributor(contributorDTO.getLogin()))
                .toList()
                .toBlocking()
                .first();
        return contributors;
    }
}
