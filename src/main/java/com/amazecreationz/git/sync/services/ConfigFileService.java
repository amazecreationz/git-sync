package com.amazecreationz.git.sync.services;

import com.amazecreationz.git.sync.GlobalConstants;
import com.amazecreationz.git.sync.models.GitCredentials;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 12:48 PM.
 */
public class ConfigFileService implements GlobalConstants {
    private File configFile;

    public ConfigFileService(String configFileName) {
        configFile = new File(configFileName);
        try {
            configFile.createNewFile();
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

    public void putGitCredentials(GitCredentials gitCredentials) {
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
            gitCredentials = new DataFetcherService().fetchGitCredentials();
            putGitCredentials(gitCredentials);
        }
        else {
            gitCredentials = new GitCredentials(username.toString(), password.toString());
        }
        return gitCredentials;
    }
}
