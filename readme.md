User Address
============

An SDN example use case.

Local build and run
-------------------

* Run a local neo4j server
* Build, then run the todo application

`mvn clean package jetty:run-war`

* Try it out

```bash
./bin/taskuser mk "Andreas Kollegger"
./bin/taskuser list
```

CLI Tools
---------

A few simplistic cli tools are in the `bin`, which directory can directly create, delete and list entities.

    Usage: {taskuser | address} [ list | mk | rm ]

      Sub-commands:
      list            - list current entities
      mk "some thing" - to create an entity
      rm 1            - to remove the entity with id 1

