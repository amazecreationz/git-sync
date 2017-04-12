package com.amazecreationz.gitsync.services;

import com.amazecreationz.gitsync.constants.GlobalConstants;
import com.amazecreationz.gitsync.constants.MessageConstants;
import com.amazecreationz.gitsync.models.SyncProject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 3:23 PM.
 */
public class ProjectManagerService implements GlobalConstants, MessageConstants {
    private ConfigFileService configFileService;
    private MessageService messageService;
    private HashMap<String, String> messageParams;

    public ProjectManagerService() {
        this.configFileService = new ConfigFileService();
        this.messageService = new MessageService();
    }

    public void addNewProject() {
        int response = FAILURE;
        while(response != NEW_PROJECT_SUCCESS) {
            SyncProject syncProject = new FetcherService().fetchProjectDetails();
            response = configFileService.putSyncProjectJSON(syncProject);
            messageParams = new HashMap<String, String>();
            messageParams.put(STRING_PROJECT_NAME, syncProject.getName());
            messageParams.put(STRING_TIME, new Date().toString());
            messageService.displayMessageFromErrorCode(response, messageParams);
        }
    }

    public JsonObject getSyncProjectJSON(String projectName) {
        JsonObject projectData = configFileService.getSyncProjectJSON(projectName);
        if (projectData == null) {
            messageParams = new HashMap<String, String>();
            messageParams.put(STRING_PROJECT_NAME, projectName);
            messageService.displayMessageFromErrorCode(ERR_NOT_FOUND, messageParams);
            projectData = new JsonObject();
        }
        return projectData;
    }

    public JsonArray getAllProjectsAsJSON() {
        return configFileService.getAllProjectsAsJSON();
    }

    public void deleteSyncProject(String projectName) {
        messageParams = new HashMap<String, String>();
        messageParams.put(STRING_PROJECT_NAME, projectName);
        messageService.displayMessageFromErrorCode(configFileService.deleteSyncProject(projectName), messageParams);
    }
}