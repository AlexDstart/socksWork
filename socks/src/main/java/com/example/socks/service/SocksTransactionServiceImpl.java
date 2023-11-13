package com.example.socks.service;

import com.example.socks.model.entity.SocksTransaction;
import com.example.socks.repository.SocksTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class SocksTransactionServiceImpl implements SocksTransactionService {
    private final SocksTransactionRepository repository;

    @Override
    public SocksTransaction addSocksTransaction(SocksTransaction socksTransaction) {
        return repository.save(socksTransaction);
    }

    @Override
    public List<SocksTransaction> getHistory(LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        return repository.findAllByTransactionDateIsBetweenOrderById(rangeStart, rangeEnd);
    }
}
