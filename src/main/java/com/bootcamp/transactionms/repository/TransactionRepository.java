package com.bootcamp.transactionms.repository;

import com.bootcamp.transactionms.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
  Flux<Transaction> findByAccountId(Long accountId);
}
