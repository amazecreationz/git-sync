package com.amazecreationz.gitsync.services;

import com.amazecreationz.gitsync.models.GitCredentials;
import com.amazecreationz.gitsync.models.SyncProject;

import java.util.Scanner;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:30 PM.
 */
public class FetcherService {
    private Scanner scanner;

    public FetcherService() {
        scanner = new Scanner(System.in);
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

    public String fetchProjectName() {
        System.out.print("Project Name: ");
        return scanner.nextLine();
    }

    public SyncProject fetchProjectDetails() {
        System.out.print("New Project Name: ");
        String projectName = scanner.nextLine();
        System.out.print("Project Source : ");
        String projectSource = scanner.nextLine();
        System.out.print("Project Destination: ");
        String projectDestination = scanner.nextLine();
        return new SyncProject(projectName, projectSource, projectDestination);
    }

    public String fetchGitCommitMessage() {
        System.out.print("Commit Message: ");
        return scanner.nextLine();
    }

}
