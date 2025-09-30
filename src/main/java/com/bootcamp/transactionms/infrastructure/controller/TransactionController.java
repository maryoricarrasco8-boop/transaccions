package com.bootcamp.transactionms.infrastructure.controller;

import com.bootcamp.transactionms.application.TransactionRepository;
import com.bootcamp.transactionms.application.TransactionService;
import com.bootcamp.transactionms.domain.Transaction;
import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TransactionController {

  private final TransactionService service;

  public TransactionController(TransactionRepository repository) {
    this.service = new TransactionService(repository);
  }

  @Bean
  public RouterFunction<ServerResponse> routes() {
    return route(POST("/transactions").and(accept(MediaType.APPLICATION_JSON)), this::create)
        .andRoute(GET("/transactions/{id}"), this::getById)
        .andRoute(GET("/transactions"), this::listAll);
  }

  private Mono<ServerResponse> create(ServerRequest req) {
    return req.bodyToMono(CreateTxRequest.class)
        .flatMap(r -> service.create(r.accountId(), r.amount()))
        .flatMap(tx -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(tx));
  }

  private Mono<ServerResponse> getById(ServerRequest req) {
    return service.getById(req.pathVariable("id"))
        .flatMap(tx -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(tx))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  private Mono<ServerResponse> listAll(ServerRequest req) {
    Flux<Transaction> all = service.listAll();
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(all, Transaction.class);
  }

  public record CreateTxRequest(String accountId, BigDecimal amount) {}
}
