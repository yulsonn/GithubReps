package ru.julappdev.githubreps.presenter.vo;

import java.io.Serializable;

/**
 * Created by yulia on 26.04.16.
 */
public class Contributor implements Serializable {
    private String name;

    public Contributor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}