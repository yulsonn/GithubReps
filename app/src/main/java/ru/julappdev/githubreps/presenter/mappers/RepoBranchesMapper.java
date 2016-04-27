package ru.julappdev.githubreps.presenter.mappers;

import java.util.List;

import ru.julappdev.githubreps.model.dto.BranchDTO;
import ru.julappdev.githubreps.presenter.vo.Branch;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by yulia on 26.04.16.
 */
public class RepoBranchesMapper implements Func1<List<BranchDTO>, List<Branch>> {

    @Override
    public List<Branch> call(List<BranchDTO> branchDTOs) {
        List<Branch> branches = Observable.from(branchDTOs)
                .map(branchDTO -> new Branch(branchDTO.getName()))
                .toList()
                .toBlocking()
                .first();
        return branches;
    }
}