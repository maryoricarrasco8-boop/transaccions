package com.bootcamp.transactionms.controller;

import com.bootcamp.transactionms.model.Transaction;
import com.bootcamp.transactionms.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(TransactionController.class)
public class TransactionControllerTest {
  @Autowired
  WebTestClient webTestClient;

  @MockBean
  TransactionService service;

  @Test
  void getNotFound() {
    when(service.findById("nope")).thenReturn(Mono.empty());
    webTestClient.get().uri("/api/v1/transactions/nope")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()  // controller returns Mono.empty -> 200 with empty body
        .expectBody().isEmpty();
  }
}
