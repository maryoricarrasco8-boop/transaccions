# TransactionMS – BTGP Final Template

Este proyecto es una **plantilla lista** para cumplir los criterios del Proyecto Final BTGP:

- ✅ JUnit 5 + Mockito (incluye pruebas reactivas con StepVerifier)
- ✅ Reporte de cobertura con **JaCoCo** (generado en `target/site/jacoco/index.html` tras `mvn verify`)
- ✅ **Checkstyle** configurado y forzado en `validate`
- ✅ Principios **SOLID**: capas separadas (puerto repositorio + servicio) y dependencias dirigidas al dominio
- ✅ Patrones: **Puerto/Adaptador** (hexagonal), repositorio en memoria como adapter
- ✅ **Postman** collection en `docs/TransactionMS.postman_collection.json`
- ✅ Archivo `sonar-project.properties` listo

## Cómo ejecutar
```bash
mvn clean verify
mvn spring-boot:run
```

## Rutas
- `POST /transactions` {accountId, amount}
- `GET /transactions/{id}`
- `GET /transactions`

## Coverage
- Abrir `target/site/jacoco/index.html` al terminar `mvn verify`.
