package com.example.socks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Table(name = "socks_warehouse")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "socks_id")
    private Socks socks;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
}
