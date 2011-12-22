package org.akollegger.trial.useraddy.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskUser extends User {

    public String toJson() {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(this);
    }

    public static String toJsonArray(Iterable<TaskUser> collection) {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(collection);
    }

    public static TaskUser fromJsonToTaskUser(String json) {
        return new JSONDeserializer<TaskUser>().use(null, TaskUser.class).deserialize(json);
    }

    public static Collection<TaskUser> fromJsonArrayToTaskUsers(String json) {
        return new JSONDeserializer<List<TaskUser>>().use(null, ArrayList.class).use("values", TaskUser.class).deserialize(json);
    }

}
