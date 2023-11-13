package com.example.socks.model.entity;

import com.example.socks.model.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "socks_transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocksTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "socks_id")
    private Socks socks;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @NotNull
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
