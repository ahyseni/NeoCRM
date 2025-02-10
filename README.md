
# Market Place structure using Mapstruct

## Docker run
Run `docker compose up -d`


## How to run for dev

```
mvn spring-boot:run -Dspring-boot.rn.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address8000"

```


## Include Jrebel support

```
mvn spring-boot:run -Dspring-boot.rn.jvmArguments="-agentPath:/FOO -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address8000"

```
