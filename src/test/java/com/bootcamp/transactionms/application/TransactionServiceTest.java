package com.bootcamp.transactionms.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bootcamp.transactionms.domain.Transaction;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @Mock
  private TransactionRepository repository;

  private TransactionService service;

  @BeforeEach
  void setup() {
    service = new TransactionService(repository);
  }

  @Test
  void create_ok() {
    when(repository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));
    StepVerifier.create(service.create("acc-1", new BigDecimal("10.00")))
        .expectNextMatches(tx -> tx.getAccountId().equals("acc-1") && tx.getAmount().compareTo(new BigDecimal("10.00"))==0)
        .verifyComplete();
  }

  @Test
  void create_rejects_zero_amount() {
    StepVerifier.create(service.create("acc-1", BigDecimal.ZERO))
        .expectError(IllegalArgumentException.class)
        .verify();
  }

  @Test
  void getById_found() {
    Transaction t = new Transaction(UUID.randomUUID().toString(),"acc", new BigDecimal("5"), Instant.now());
    when(repository.findById(t.getId())).thenReturn(Mono.just(t));
    StepVerifier.create(service.getById(t.getId()))
        .expectNext(t)
        .verifyComplete();
  }

  @Test
  void listAll_ok() {
    when(repository.findAll()).thenReturn(Flux.empty());
    StepVerifier.create(service.listAll()).verifyComplete();
  }
}
