package com.bootcamp.transactionms.controller;

import com.bootcamp.transactionms.model.Transaction;
import com.bootcamp.transactionms.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
  private final TransactionService service;
  public TransactionController(TransactionService service) { this.service = service; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Transaction> create(@Valid @RequestBody Transaction t) {
    return service.create(t);
  }

  @GetMapping("/<built-in function id>")
  public Mono<Transaction> get(@PathVariable String id) {
    return service.findById(id);
  }

  @GetMapping
  public Flux<Transaction> list(@RequestParam(value = "accountId", required = false) Long accountId) {
    return accountId == null ? service.list() : service.listByAccount(accountId);
  }
}
