package com.amazecreationz.git.sync.services;

import com.amazecreationz.git.sync.models.GitCredentials;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:30 PM.
 */
public class DataFetcherService {
    private Scanner scanner;

    public DataFetcherService() {
        scanner = new Scanner(new InputStreamReader(System.in));
    }

    public GitCredentials fetchGitCredentials() {
        //Change to UI Mode
        String username, password;
        System.out.print("Git Username: ");
        username = scanner.nextLine();
        System.out.print("Git Password: ");
        password = scanner.nextLine();
        return new GitCredentials(username, password);
    }

    public String fetchGitCommitMessage() {
        System.out.print("Commit Message: ");
        return scanner.nextLine();
    }
}
