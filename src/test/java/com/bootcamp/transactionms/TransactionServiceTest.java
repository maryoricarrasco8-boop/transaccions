package com.bootcamp.transactionms.service;

import com.bootcamp.transactionms.events.TransactionProducer;
import com.bootcamp.transactionms.model.Transaction;
import com.bootcamp.transactionms.repository.TransactionRepository;
import com.bootcamp.transactionms.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
  @Test
  void createPublishesEvent() {
    TransactionRepository repo = mock(TransactionRepository.class);
    TransactionProducer producer = mock(TransactionProducer.class);
    TransactionServiceImpl svc = new TransactionServiceImpl(repo, producer);

    when(repo.save(any(Transaction.class))).thenAnswer(inv -> {
      Transaction t = inv.getArgument(0);
      t.setId("tx-1");
      return Mono.just(t);
    });

    Mono<Transaction> result = svc.create(Transaction.builder().accountId(1L).type("DEPOSIT").amount(50.0).build());
    StepVerifier.create(result)
        .expectNextMatches(t -> t.getId()!=null && t.getAmount()==50.0)
        .verifyComplete();
    verify(producer, times(1)).publish(any());
  }
}
