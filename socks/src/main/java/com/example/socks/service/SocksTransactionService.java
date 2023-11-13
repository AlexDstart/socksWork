package com.example.socks.service;

import com.example.socks.model.entity.SocksTransaction;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface SocksTransactionService {
    SocksTransaction addSocksTransaction(@NotNull SocksTransaction socksTransaction);

    List<SocksTransaction> getHistory(@NotNull LocalDateTime rangeStart, @NotNull LocalDateTime rangeEnd);
}
