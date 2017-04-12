package com.amazecreationz.gitsync.services;

import com.amazecreationz.gitsync.constants.GlobalConstants;
import com.amazecreationz.gitsync.constants.MessageConstants;
import com.amazecreationz.gitsync.models.GitCredentials;
import com.amazecreationz.gitsync.models.SyncProject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;


/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:48 PM.
 */
public class ConfigFileService implements GlobalConstants, MessageConstants {
    private File configFile;

    public ConfigFileService() {
        this.configFile = new File(CONFIG_FILE);
        try {
            this.configFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConfigFileService(String configFileName) {
        this.configFile = new File(configFileName);
        try {
            this.configFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject readConfigFileAsJSON() {
        String configFileString;
        JsonObject configFileJSON = new JsonObject();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(configFile));
            configFileString = bufferedReader.readLine();
            bufferedReader.close();
            if(configFileString != null) {
                configFileJSON = new JsonParser().parse(configFileString).getAsJsonObject();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return configFileJSON;
    }

    private void writeConfigFileFromJSON(JsonObject configFileJSON) {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(configFile));
            bufferedWriter.write(configFileJSON.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putGitCredentials(GitCredentials gitCredentials) {
        JsonObject configFileJSON = readConfigFileAsJSON();
        configFileJSON.addProperty(STRING_USERNAME, gitCredentials.getUsername());
        configFileJSON.addProperty(STRING_PASSWORD, gitCredentials.getPassword());
        writeConfigFileFromJSON(configFileJSON);
    }

    public GitCredentials getGitCredentials() {
        JsonObject configFileJSON = readConfigFileAsJSON();
        GitCredentials gitCredentials;
        JsonElement username = configFileJSON.get(STRING_USERNAME);
        JsonElement password = configFileJSON.get(STRING_PASSWORD);
        if(username == null || password == null) {
            gitCredentials = new FetcherService().fetchGitCredentials();
            putGitCredentials(gitCredentials);
        }
        else {
            gitCredentials = new GitCredentials(username.getAsString(), password.getAsString());
        }
        return gitCredentials;
    }

    public int putSyncProjectJSON(SyncProject syncProject) {
        JsonObject configFileJSON = readConfigFileAsJSON();
        JsonElement projectsJSON = configFileJSON.get(STRING_PROJECTS);
        JsonArray projectsJSONArray;
        if(projectsJSON == null) {
            projectsJSONArray = new JsonArray();
        }
        else {
            projectsJSONArray = projectsJSON.getAsJsonArray();
            JsonObject projectJSON = getSyncProjectJSON(syncProject.getName());
            if(projectJSON != null) {
                return ERR_DUPLICATE;
            }
        }
        projectsJSONArray.add(syncProject.getSyncProjectJSON());
        configFileJSON.add(STRING_PROJECTS, projectsJSONArray);
        writeConfigFileFromJSON(configFileJSON);
        return NEW_PROJECT_SUCCESS;
    }

    public JsonObject getSyncProjectJSON(String projectName) {
        JsonObject configFileJSON = readConfigFileAsJSON();
        JsonElement projectsJSON = configFileJSON.get(STRING_PROJECTS);
        if(projectsJSON != null) {
            JsonArray projectsJSONArray = projectsJSON.getAsJsonArray();
            for(JsonElement projectJSON: projectsJSONArray) {
                JsonObject projectDataJSON = projectJSON.getAsJsonObject();
                if(projectDataJSON.get(STRING_PROJECT_NAME).getAsString().equals(projectName)) {
                    return projectDataJSON;
                }
            }
        }
        return null;
    }

    public JsonArray getAllProjectsAsJSON() {
        JsonObject configFileJSON = readConfigFileAsJSON();
        JsonElement projectsJSON = configFileJSON.get(STRING_PROJECTS);
        return projectsJSON.getAsJsonArray();
    }

    public int deleteSyncProject(String projectName) {
        JsonObject configFileJSON = readConfigFileAsJSON();
        JsonElement projectsJSON = configFileJSON.get(STRING_PROJECTS);
        if(projectsJSON != null) {
            JsonArray projectsJSONArray = projectsJSON.getAsJsonArray();
            for(JsonElement projectJSON: projectsJSONArray) {
                JsonObject projectDataJSON = projectJSON.getAsJsonObject();
                if(projectDataJSON.get(STRING_PROJECT_NAME).getAsString().equals(projectName)) {
                    projectsJSONArray.remove(projectJSON);
                    configFileJSON.add(STRING_PROJECTS, projectsJSONArray);
                    writeConfigFileFromJSON(configFileJSON);
                    return PROJECT_DELETE_SUCCESS;
                }
            }
        }
        return PROJECT_DELETE_FAILURE;
    }
}