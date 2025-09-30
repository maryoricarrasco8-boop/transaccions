package com.bootcamp.transactionms.infrastructure.controller;

import com.bootcamp.transactionms.application.TransactionRepository;
import com.bootcamp.transactionms.infrastructure.repository.InMemoryTransactionRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class TransactionControllerTest {

  private WebTestClient client;

  @BeforeEach
  void setup() {
    TransactionRepository repo = new InMemoryTransactionRepository();
    TransactionController controller = new TransactionController(repo);
    client = WebTestClient.bindToRouterFunction(controller.routes()).build();
  }

  @Test
  void create_and_get() {
    var req = new TransactionController.CreateTxRequest("acc-1", new BigDecimal("12.34"));
    var created = client.post().uri("/transactions")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(req)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.accountId").isEqualTo("acc-1")
        .returnResult();

    // Extract id and fetch
    String response = new String(created.getResponseBody());
    String id = response.replaceAll(".*\"id\":\"([^\"]+)\".*", "$1");

    client.get().uri("/transactions/{id}", id)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON);
  }
}
