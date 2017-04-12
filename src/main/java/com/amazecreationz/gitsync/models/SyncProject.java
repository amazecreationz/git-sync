package com.amazecreationz.gitsync.models;

import com.amazecreationz.gitsync.constants.GlobalConstants;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 12/4/17 3:11 PM.
 */
public class SyncProject implements GlobalConstants {
    private String name;
    private String source;
    private String destination;
    private ArrayList<String> syncIgnore;

    public SyncProject(String name, String source, String destination) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.syncIgnore = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void addSyncIgnoreDirectory(String ignoreDirectory) {
        syncIgnore.add(ignoreDirectory);
    }

    public JsonObject getSyncProjectJSON() {
        JsonObject projectJSON = new JsonObject();
        projectJSON.addProperty(STRING_PROJECT_NAME, name);
        projectJSON.addProperty(STRING_PROJECT_SOURCE, source);
        projectJSON.addProperty(STRING_PROJECT_DESTINATION, destination);
        return projectJSON;
    }
}
