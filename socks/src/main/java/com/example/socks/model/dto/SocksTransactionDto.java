package com.example.socks.model.dto;

import com.example.socks.model.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksTransactionDto {

    @NotBlank
    private String color;

    @NotNull
    @Min(value = 0, message = "CottonPart must be greater than or equal to 0")
    @Max(value = 100, message = "CottonPart must be less than or equal to 100")
    private Integer cottonPart;

    @NotNull
    private OperationType operationType;

    @NotNull
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @NotNull
    private LocalDateTime transactionDate;
}
