package org.akollegger.trial.useraddy.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NodeEntity
@Configurable
public class Address {

    @SuppressWarnings("unused")
	@GraphId
    private Long id;

    private String name;

    public Address() {;}

    public Address(Node n) {
        setPersistentState(n);
    }
    
    public Long getId() {
        return getNodeId();
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName());
        return sb.toString();
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(this);
    }    

    public static String toJsonArray(Iterable<Address> collection) {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(collection);
    }

    public static Address fromJsonToTodo(String json) {
        return new JSONDeserializer<Address>().use(null, Address.class).deserialize(json);
    }
    
    public static Collection<Address> fromJsonArrayToTodoes(String json) {
        return new JSONDeserializer<List<Address>>().use(null, ArrayList.class).use("values", Address.class).deserialize(json);
    }

}
