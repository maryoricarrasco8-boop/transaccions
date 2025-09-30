package com.bootcamp.transactionms.service;

import com.bootcamp.transactionms.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
  Mono<Transaction> create(Transaction t);
  Mono<Transaction> findById(String id);
  Flux<Transaction> list();
  Flux<Transaction> listByAccount(Long accountId);
}
