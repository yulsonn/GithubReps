package ru.julappdev.githubreps.view.adapters;

import java.util.List;

import ru.julappdev.githubreps.presenter.vo.Contributor;

/**
 * Created by yulia on 27.04.16.
 */
public class ContributorsAdapter extends BaseAdapter<Contributor> {

    public ContributorsAdapter(List<Contributor> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        String text = list.get(i).getName();
        viewHolder.text.setText(text);
    }
}