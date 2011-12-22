package org.akollegger.trial.useraddy.repository;

import org.akollegger.trial.useraddy.model.TaskUser;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AddressRepository extends GraphRepository<TaskUser> {

}
