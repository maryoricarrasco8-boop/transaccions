# Análisis de la solución (SOLID + Patrones)

## SOLID
1. **SRP**: `TransactionServiceImpl` concentra lógica de negocio; control de transporte en `TransactionController` y persistencia en `TransactionRepository`.
2. **OCP**: Nuevos tipos de transacción pueden añadirse sin modificar controladores (validaciones en servicio).
3. **LSP**: `TransactionService` define contrato implementado por `TransactionServiceImpl`.
4. **ISP**: Interfaces pequeñas y enfocadas.
5. **DIP**: El servicio depende de abstracciones (`TransactionRepository`, `TransactionProducer`) inyectadas.

## Patrones
- **Repository** (ReactiveMongoRepository).
- **Port-Adapter (Hexagonal)**: `TransactionProducer` como adapter de salida para eventos Kafka.
- **Builder** (Lombok) para crear `Transaction`.

## Decisiones
- **MongoDB Reactive** para alinearse con **WebFlux** y backpressure.
- **Redis** para cache en lecturas por id.
- **Kafka** para eventos de creación, desacoplando consumidores.
