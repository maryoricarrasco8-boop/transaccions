package com.bootcamp.transactionms.application;

import com.bootcamp.transactionms.domain.Transaction;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransactionService {

  private final TransactionRepository repository;

  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }

  public Mono<Transaction> create(String accountId, BigDecimal amount) {
    if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
      return Mono.error(new IllegalArgumentException("Amount must be non-zero"));
    }
    Transaction tx = new Transaction(UUID.randomUUID().toString(), accountId, amount, Instant.now());
    return repository.save(tx);
  }

  public Mono<Transaction> getById(String id) {
    return repository.findById(id);
  }

  public Flux<Transaction> listAll() {
    return repository.findAll();
  }
}
