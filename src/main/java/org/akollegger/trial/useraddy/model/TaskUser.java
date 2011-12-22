package org.akollegger.trial.useraddy.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.*;

public class TaskUser extends User {

    @Fetch @RelatedTo( type="HAS_ADDRESS", elementClass = Address.class, direction = Direction.BOTH )
    private Set<Address> addresses = new HashSet<Address>();

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

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
