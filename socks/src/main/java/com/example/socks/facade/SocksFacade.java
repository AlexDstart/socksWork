package com.example.socks.facade;

import com.example.socks.model.dto.SocksDto;
import com.example.socks.model.dto.SocksTransactionDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface SocksFacade {
    SocksDto addSocks(@NotNull SocksDto socksDto);

    SocksDto deleteSocks(@NotNull SocksDto socksDto);

    List<SocksDto> getAll(@NotNull String color, @NotNull String operation, @NotNull Integer cottonPart);

    List<SocksTransactionDto> getHistory(@NotNull LocalDateTime rangeStart, @NotNull LocalDateTime rangeEnd);
}
