package ru.julappdev.githubreps.view.adapters;

import java.util.List;

import ru.julappdev.githubreps.presenter.RepoListPresenter;
import ru.julappdev.githubreps.presenter.vo.Repository;

/**
 * Created by yulia on 27.04.16.
 */
public class RepoListAdapter extends BaseAdapter<Repository> {
    private RepoListPresenter presenter;


    public RepoListAdapter(List<Repository> list, RepoListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        Repository repo = list.get(i);
        viewHolder.text.setText(repo.getRepoName());
        viewHolder.text.setOnClickListener(v -> presenter.clickRepo(repo));
    }

    public void setRepoList(List<Repository> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}