package ru.julappdev.githubreps.common;

import android.app.Application;

import ru.julappdev.githubreps.injection.component.AppComponent;
import ru.julappdev.githubreps.injection.component.DaggerAppComponent;

/**
 * Created by yulia on 01.05.16.
 */
public class App extends Application {

    private static AppComponent component;

    public static  AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder().build();
    }
}
