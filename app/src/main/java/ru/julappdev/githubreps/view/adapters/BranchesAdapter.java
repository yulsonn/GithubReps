package ru.julappdev.githubreps.view.adapters;

import java.util.List;

import ru.julappdev.githubreps.presenter.vo.Branch;

/**
 * Created by yulia on 27.04.16.
 */
public class BranchesAdapter extends BaseAdapter<Branch> {

    public BranchesAdapter(List<Branch> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        String text = list.get(position).getName();
        holder.text.setText(text);
    }
}