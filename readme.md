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
./bin/address mk "1609 Lancaster"
./bin/taskuser rel 1 2
```

That last command should add the address "1609 Lancaster" to the user "Andreas Kollegger".

CLI Tools
---------

A few simplistic cli tools are in the `bin`, which directory can directly create, delete and list entities.

    Usage: {taskuser | address} [ list | mk | rm | rel ]

      Sub-commands:
      list            - list current entities
      mk "some thing" - to create an entity
      rm 1            - to remove the entity with id 1
      rel 1 2         - to relate id 1 with id 2

