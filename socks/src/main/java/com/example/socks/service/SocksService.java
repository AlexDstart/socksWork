package com.example.socks.service;

import com.example.socks.model.entity.Socks;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface SocksService {
    Socks addSocks(Socks socks);

    Optional<Socks> findByColorAndCottonPart(@NotNull String color, @NotNull Integer cottonPart);

    List<Socks> getAllByColorAndCottonPartOperation(String color, String operation, Integer cottonPart);
}
