package com.amazecreationz.git.sync;

import com.amazecreationz.git.sync.models.GitCredentials;
import com.amazecreationz.git.sync.services.ConfigFileService;
import com.amazecreationz.git.sync.services.DataFetcherService;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:20 PM.
 */
public class StartSync implements GlobalConstants {
    public static void main(String[] args) {
        //UI Call Here
        ConfigFileService configFileService = new ConfigFileService(CONFIG_FILE);
        GitCredentials gitCredentials = configFileService.getGitCredentials();
        System.out.println(gitCredentials.getUsername());
        System.out.println(gitCredentials.getPassword());
    }
}
