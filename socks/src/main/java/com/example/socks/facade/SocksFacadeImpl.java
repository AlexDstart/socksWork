package com.example.socks.facade;

import com.example.socks.exception.SocksNotFoundException;
import com.example.socks.model.dto.SocksDto;
import com.example.socks.model.dto.SocksTransactionDto;
import com.example.socks.model.entity.Socks;
import com.example.socks.model.entity.SocksTransaction;
import com.example.socks.model.entity.Warehouse;
import com.example.socks.model.enums.OperationType;
import com.example.socks.service.SocksService;
import com.example.socks.service.SocksTransactionService;
import com.example.socks.service.WarehouseService;
import com.example.socks.util.mapper.SocksMapper;
import com.example.socks.util.mapper.SocksTransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.socks.model.enums.OperationType.INCOME;
import static com.example.socks.model.enums.OperationType.OUTCOME;
import static com.example.socks.util.Constants.SOCKS_NOT_FOUND;

@Component
@Validated
@AllArgsConstructor
public class SocksFacadeImpl implements SocksFacade {
    private final SocksService socksService;
    private final WarehouseService warehouseService;
    private final SocksTransactionService socksTransactionService;
    private final SocksMapper socksMapper;
    private final SocksTransactionMapper socksTransactionMapper;

    @Override
    public SocksDto addSocks(SocksDto socksDto) {
        Optional<Socks> optionalSocks = socksService.findByColorAndCottonPart(socksDto.getColor(),
                socksDto.getCottonPart());
        Socks socks = socksMapper.socksDtoToSocks(socksDto);
        if (optionalSocks.isEmpty()) {
            socks = socksService.addSocks(socks);
        } else {
            socks = optionalSocks.get();
        }
        Integer quantity = socksDto.getQuantity();
        Warehouse warehouse = warehouseService.addIncomeTransaction(socks, quantity);
        socksDto.setQuantity(warehouse.getQuantity());
        createTransaction(socks, INCOME, quantity);
        return socksDto;
    }

    @Override
    public SocksDto deleteSocks(SocksDto socksDto) {
        Socks socks = socksService.findByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart()).orElseThrow(
                () -> new SocksNotFoundException(SOCKS_NOT_FOUND));
        Integer quantity = socksDto.getQuantity();
        Warehouse warehouse = warehouseService.addOutcomeTransaction(socks, quantity);
        socksDto.setQuantity(warehouse.getQuantity());
        createTransaction(socks, OUTCOME, quantity);
        return socksDto;
    }

    @Override
    public List<SocksDto> getAll(String color, String operation, Integer cottonPart) {
        List<Socks> socks = socksService.getAllByColorAndCottonPartOperation(color, operation, cottonPart);
        List<Integer> socksIds = socks.stream().map(Socks::getId).collect(Collectors.toList());
        List<Warehouse> warehouses = warehouseService.getAllBySocks(socksIds);
        return warehouses.stream().map(warehouse -> socksMapper
                .socksToSocksDto(warehouse.getSocks(), warehouse.getQuantity())).collect(Collectors.toList());
    }

    @Override
    public List<SocksTransactionDto> getHistory(LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        List<SocksTransaction> socksTransactions = socksTransactionService.getHistory(rangeStart, rangeEnd);
        return socksTransactions.stream()
                .map(socksTransactionMapper::socksTransactionToSocksTransactionDto).collect(Collectors.toList());
    }

    private void createTransaction(Socks socks, OperationType operationType, Integer quantity) {
        SocksTransaction socksTransaction = SocksTransaction.builder()
                .socks(socks)
                .operationType(operationType)
                .quantity(quantity)
                .transactionDate(LocalDateTime.now())
                .build();
        socksTransactionService.addSocksTransaction(socksTransaction);
    }

}
