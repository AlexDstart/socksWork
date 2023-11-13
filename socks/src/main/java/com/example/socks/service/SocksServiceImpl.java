package com.example.socks.service;

import com.example.socks.exception.OperationNotFoundException;
import com.example.socks.exception.SocksUniqueException;
import com.example.socks.model.entity.Socks;
import com.example.socks.repository.SocksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.socks.util.Constants.*;

@Service
@AllArgsConstructor
@Validated
public class SocksServiceImpl implements SocksService {
    private final SocksRepository repository;

    @Override
    public Socks addSocks(Socks socks) {
        Optional<Socks> optionalSocks = repository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (optionalSocks.isPresent()) {
            throw new SocksUniqueException(SOCKS_EXIST);
        }
        return repository.save(socks);
    }

    @Override
    public List<Socks> getAllByColorAndCottonPartOperation(String color, String operation, Integer cottonPart) {
        checkOperation(operation);
        return getSocksByColorAndOperation(color, operation, cottonPart);
    }

    @Override
    public Optional<Socks> findByColorAndCottonPart(String color, Integer cottonPart) {
        return repository.findByColorAndCottonPart(color, cottonPart);
    }

    private void checkOperation(String operation) {
        if (!MORE_THAN_OPERATION.equals(operation) && !LESS_THAN_OPERATION.equals(operation)
                && !EQUAL_OPERATION.equals(operation)) {
            throw new OperationNotFoundException(OPERATION_NOT_FOUND);
        }
    }

    private List<Socks> getSocksByColorAndOperation(String color, String operation, Integer cottonPart) {
        return switch (operation) {
            case MORE_THAN_OPERATION -> repository.findByColorAndCottonPartGreaterThan(color, cottonPart);
            case LESS_THAN_OPERATION -> repository.findByColorAndCottonPartLessThan(color, cottonPart);
            case EQUAL_OPERATION -> repository.findByColorAndCottonPartEquals(color, cottonPart);
            default -> new ArrayList<>();
        };
    }
}
