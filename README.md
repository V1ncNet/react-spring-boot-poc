# React + Spring Web PoC

This project contains two separate components. A server and a client component.
Both may run independently. But for "production" the client is bundles with the
server. Spring Web serves them as static resources.

To test this run the following commands:

```shell
$ mvn -f apps/server package
$ java -jar apps/server/target/react-spring-boot-server-0.1.0-SNAPSHOT.jar
```

You may also run the main class with your favourite IDE instead. But make sure
to run the `package` Maven target before.
