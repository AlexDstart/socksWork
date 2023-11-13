package com.example.socks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "socks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Color is mandatory")
    private String color;

    @NotNull(message = "CottonPart is mandatory")
    @Min(value = 0, message = "CottonPart must be greater than or equal to 0")
    @Max(value = 100, message = "CottonPart must be less than or equal to 100")
    @Column(name = "cotton_part")
    private Integer cottonPart;
}
