# API Itajufácil

## Configuração do container PostgreSQL

```
docker run --name postgres-itajufacil \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=1 \
  -e POSTGRES_DB=api-itajufacil \
  -p 5432:5432 \
  -d postgres:17
```

## Configuração do application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/api-itajufacil
spring.datasource.username=postgres
spring.datasource.password=1
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

