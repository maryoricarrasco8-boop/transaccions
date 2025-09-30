package com.bootcamp.transactionms.events;

public record TransactionEvent(String type, String txId, Long accountId, Double amount) {
  public static TransactionEvent created(String id, Long accountId, Double amount) {
    return new TransactionEvent("TRANSACTION_CREATED", id, accountId, amount);
  }
}
