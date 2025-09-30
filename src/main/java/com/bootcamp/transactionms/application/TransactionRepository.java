package com.bootcamp.transactionms.application;

import com.bootcamp.transactionms.domain.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository {
  Mono<Transaction> save(Transaction tx);
  Mono<Transaction> findById(String id);
  Flux<Transaction> findAll();
}
