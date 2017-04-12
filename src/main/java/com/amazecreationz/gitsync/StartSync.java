package com.amazecreationz.gitsync;

import com.amazecreationz.gitsync.constants.GlobalConstants;
import com.amazecreationz.gitsync.models.GitCredentials;
import com.amazecreationz.gitsync.services.ConfigFileService;
import com.amazecreationz.gitsync.services.ProjectManagerService;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:20 PM.
 */
public class StartSync implements GlobalConstants {
    public static void main(String[] args) {
        //UI Call Here
        ConfigFileService configFileService = new ConfigFileService();
        GitCredentials gitCredentials = configFileService.getGitCredentials();
        ProjectManagerService projectManagerService = new ProjectManagerService();
        //projectManagerService.addNewProject();
        //System.out.println(projectManagerService.getAllProjectsAsJSON());
        //System.out.println(projectManagerService.getSyncProjectJSON());
        projectManagerService.deleteSyncProject("amaze");
    }
}
