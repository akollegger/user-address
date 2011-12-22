package org.akollegger.trial.useraddy.repository;

import org.akollegger.neo4j.scsdn.Todo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TaskUserRepository extends GraphRepository<Todo> {

}
