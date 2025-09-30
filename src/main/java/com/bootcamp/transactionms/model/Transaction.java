package com.bootcamp.transactionms.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;

@Document(collection = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
  @Id
  private String id;

  @NotNull
  private Long accountId;

  @NotBlank
  private String type; // DEPOSIT, WITHDRAWAL, TRANSFER

  @NotNull
  @Positive
  private Double amount;

  private String currency; // PEN/USD

  private OffsetDateTime createdAt;
}
