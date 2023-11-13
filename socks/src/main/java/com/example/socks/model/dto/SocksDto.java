package com.example.socks.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksDto {
    private Integer id;

    @NotBlank(message = "Color is mandatory")
    private String color;

    @NotNull(message = "CottonPart is mandatory")
    @Min(value = 0, message = "CottonPart must be greater than or equal to 0")
    @Max(value = 100, message = "CottonPart must be less than or equal to 100")
    private Integer cottonPart;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
}
