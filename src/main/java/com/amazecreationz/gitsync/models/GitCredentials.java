package com.amazecreationz.gitsync.models;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:28 PM.
 */
public class GitCredentials {
    private String username;
    private String password;

    public GitCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
