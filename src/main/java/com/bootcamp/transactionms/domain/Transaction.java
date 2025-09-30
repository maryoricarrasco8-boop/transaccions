package com.bootcamp.transactionms.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Transaction {
  private String id;
  private String accountId;
  private BigDecimal amount;
  private Instant createdAt;

  public Transaction(String id, String accountId, BigDecimal amount, Instant createdAt) {
    this.id = id;
    this.accountId = accountId;
    this.amount = amount;
    this.createdAt = createdAt;
  }

  public String getId() { return id; }
  public String getAccountId() { return accountId; }
  public BigDecimal getAmount() { return amount; }
  public Instant getCreatedAt() { return createdAt; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() { return Objects.hash(id); }
}
