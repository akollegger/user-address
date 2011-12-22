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

    private String text;

    public Address() {;}

    public Address(Node n) {
        setPersistentState(n);
    }
    
    public Long getId() {
        return getNodeId();
    }

    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Text: ").append(getText());
        return sb.toString();
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(this);
    }    

    public static String toJsonArray(Iterable<Address> collection) {
        return new JSONSerializer().exclude("*.class", "*.persistentState", "*.entityState").serialize(collection);
    }

    public static Address fromJsonToAddress(String json) {
        return new JSONDeserializer<Address>().use(null, Address.class).deserialize(json);
    }
    
    public static Collection<Address> fromJsonArrayToAddresses(String json) {
        return new JSONDeserializer<List<Address>>().use(null, ArrayList.class).use("values", Address.class).deserialize(json);
    }

}
