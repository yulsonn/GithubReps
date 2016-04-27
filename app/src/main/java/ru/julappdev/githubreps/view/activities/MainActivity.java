package ru.julappdev.githubreps.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.julappdev.githubreps.R;
import ru.julappdev.githubreps.presenter.vo.Repository;
import ru.julappdev.githubreps.view.ActivityCallback;
import ru.julappdev.githubreps.view.fragments.RepoInfoFragment;
import ru.julappdev.githubreps.view.fragments.RepoListFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private static String TAG = "TAG";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new RepoListFragment(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        replaceFragment(RepoInfoFragment.newInstance(repository), true);
    }

}