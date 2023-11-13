package com.example.socks.service;

import com.example.socks.model.entity.Socks;
import com.example.socks.model.entity.Warehouse;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface WarehouseService {
    Warehouse addIncomeTransaction(@NotNull Socks socks, @NotNull Integer quantity);

    Warehouse addOutcomeTransaction(@NotNull Socks socks, @NotNull Integer quantity);

    List<Warehouse> getAllBySocks(@NotNull List<Integer> socksIds);
}
