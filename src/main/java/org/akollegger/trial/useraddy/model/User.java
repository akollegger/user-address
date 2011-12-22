package org.akollegger.trial.useraddy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@NodeEntity
@Configurable
public class User {

    @SuppressWarnings("unused")
	@GraphId
    private Long id;

    private String title;

    private Boolean isDone = false;

    public User() {;}

    public User(Node n) {
        setPersistentState(n);
    }
    
    public Long getId() {
        return getNodeId();
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Boolean getIsDone() {
        return this.isDone;
    }
    
    public void setIsDone(Boolean done) {
        this.isDone = done;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Done: ").append(getIsDone()).append(", ");
        sb.append("Title: ").append(getTitle());
        return sb.toString();
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(this);
    }    

    public static String toJsonArray(Iterable<User> collection) {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(collection);
    }

    public static User fromJsonToTodo(String json) {
        return new JSONDeserializer<User>().use(null, User.class).deserialize(json);
    }
    
    public static Collection<User> fromJsonArrayToTodoes(String json) {
        return new JSONDeserializer<List<User>>().use(null, ArrayList.class).use("values", User.class).deserialize(json);
    }

}
