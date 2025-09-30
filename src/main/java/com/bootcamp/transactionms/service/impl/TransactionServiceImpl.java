package com.bootcamp.transactionms.service.impl;

import com.bootcamp.transactionms.events.TransactionEvent;
import com.bootcamp.transactionms.events.TransactionProducer;
import com.bootcamp.transactionms.model.Transaction;
import com.bootcamp.transactionms.repository.TransactionRepository;
import com.bootcamp.transactionms.service.TransactionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repo;
  private final TransactionProducer producer;

  public TransactionServiceImpl(TransactionRepository repo, TransactionProducer producer) {
    this.repo = repo;
    this.producer = producer;
  }

  @Override
  public Mono<Transaction> create(Transaction t) {
    t.setCreatedAt(OffsetDateTime.now());
    return repo.save(t)
              .doOnSuccess(saved -> producer.publish(TransactionEvent.created(saved.getId(), saved.getAccountId(), saved.getAmount())));
  }

  @Override
  @Cacheable(cacheNames = "txById", key = "#id")
  public Mono<Transaction> findById(String id) {
    return repo.findById(id);
  }

  @Override
  public Flux<Transaction> list() {
    return repo.findAll();
  }

  @Override
  public Flux<Transaction> listByAccount(Long accountId) {
    return repo.findByAccountId(accountId);
  }
}
