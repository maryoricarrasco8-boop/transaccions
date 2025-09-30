# Evidencias de ejecución

- Jacoco: `target/site/jacoco/index.html`
- Checkstyle: consola y `target/checkstyle-result.xml`
- Swagger: `http://localhost:8081/swagger-ui/index.html` (o `/swagger-ui.html`)
- Docker:
  - `docker ps`
  - `docker logs transactionms`
- Mongo Shell:
  - `docker exec -it <mongo_container> mongosh`
  - `db.transactions.find().limit(5)`
