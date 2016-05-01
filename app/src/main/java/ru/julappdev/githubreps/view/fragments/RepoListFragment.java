package ru.julappdev.githubreps.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.julappdev.githubreps.R;
import ru.julappdev.githubreps.injection.component.DaggerViewComponent;
import ru.julappdev.githubreps.injection.component.ViewComponent;
import ru.julappdev.githubreps.injection.module.ViewDynamicModule;
import ru.julappdev.githubreps.presenter.BasePresenter;
import ru.julappdev.githubreps.presenter.RepoListPresenter;
import ru.julappdev.githubreps.presenter.vo.Repository;
import ru.julappdev.githubreps.view.ActivityCallback;
import ru.julappdev.githubreps.view.RepoListView;
import ru.julappdev.githubreps.view.adapters.RepoListAdapter;

/**
 * Created by yulia on 28.04.16.
 */
public class RepoListFragment extends BaseFragment implements RepoListView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.edit_text)
    EditText editText;

    @Bind(R.id.button_search)
    Button searchButton;

    @Inject
    RepoListPresenter presenter;

    private RepoListAdapter adapter;

    private ActivityCallback activityCallback;

    private ViewComponent viewComponent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this, activityCallback))
                    .build();
        }
        viewComponent.inject(this);
    }

    public void setViewComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapter(new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> presenter.onSearchButtonClick());

        presenter.onCreateView(savedInstanceState);

        return view;
    }


    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showRepoList(List<Repository> repoList) {
        adapter.setRepoList(repoList);
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        activityCallback.startRepoInfoFragment(repository);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public String getUserName() {
        return editText.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}