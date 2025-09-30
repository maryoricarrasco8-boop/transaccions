package com.bootcamp.transactionms.infrastructure.repository;

import com.bootcamp.transactionms.application.TransactionRepository;
import com.bootcamp.transactionms.domain.Transaction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class InMemoryTransactionRepository implements TransactionRepository {

  private final Map<String, Transaction> store = new ConcurrentHashMap<>();

  @Override
  public Mono<Transaction> save(Transaction tx) {
    store.put(tx.getId(), tx);
    return Mono.just(tx);
  }

  @Override
  public Mono<Transaction> findById(String id) {
    return Mono.justOrEmpty(store.get(id));
  }

  @Override
  public Flux<Transaction> findAll() {
    return Flux.fromIterable(store.values());
  }
}
