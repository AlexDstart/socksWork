package com.example.socks.service;

import com.example.socks.exception.SocksNotEnoughException;
import com.example.socks.exception.SocksNotFoundException;
import com.example.socks.model.entity.Socks;
import com.example.socks.model.entity.Warehouse;
import com.example.socks.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import static com.example.socks.util.Constants.SOCKS_NOT_ENOUGH;
import static com.example.socks.util.Constants.SOCKS_NOT_FOUND;

@Service
@Validated
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository repository;

    @Transactional
    @Override
    public Warehouse addIncomeTransaction(Socks socks, Integer quantity) {
        Optional<Warehouse> optionalWarehouse = repository.findBySocksId(socks.getId());
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            Integer oldQuantity = warehouse.getQuantity();
            warehouse.setQuantity(oldQuantity + quantity);
            return warehouse;
        } else {
            Warehouse newWarehouse = Warehouse.builder()
                    .socks(socks)
                    .quantity(quantity)
                    .build();
            repository.save(newWarehouse);
            return newWarehouse;
        }
    }

    @Transactional
    @Override
    public Warehouse addOutcomeTransaction(Socks socks, Integer quantity) {
        Warehouse warehouse = repository.findBySocksId(socks.getId()).orElseThrow(
                () -> new SocksNotFoundException(SOCKS_NOT_FOUND));
        Integer oldQuantity = warehouse.getQuantity();
        if (oldQuantity < quantity) {
            throw new SocksNotEnoughException(SOCKS_NOT_ENOUGH);
        }
        warehouse.setQuantity(oldQuantity - quantity);
        return warehouse;
    }

    @Override
    public List<Warehouse> getAllBySocks(List<Integer> socksIds) {
        return repository.findBySocksIdIn(socksIds);
    }

}
