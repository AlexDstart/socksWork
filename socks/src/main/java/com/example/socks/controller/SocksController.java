package com.example.socks.controller;

import com.example.socks.facade.SocksFacade;
import com.example.socks.model.dto.SocksDto;
import com.example.socks.model.dto.SocksTransactionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.socks.util.Constants.DATE_TIME_FORMAT_PATTERN;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
@Slf4j
public class SocksController {

    private final SocksFacade socksFacade;

    @PostMapping("/income")
    public SocksDto addSocks(@Valid @RequestBody SocksDto socksDto) {
        log.info("Receipt of socks in the warehouse");
        return socksFacade.addSocks(socksDto);
    }

    @DeleteMapping("/outcome")
    public SocksDto deleteSocks(@Valid @RequestBody SocksDto socksDto) {
        log.info("Releasing socks in the warehouse");
        return socksFacade.deleteSocks(socksDto);
    }

    @GetMapping
    public List<SocksDto> getAll(@RequestParam String color,
                                 @RequestParam String operation,
                                 @RequestParam @Min(0) @Max(100) Integer cottonPart) {
        log.info("Receiving information about socks in the warehouse");
        return socksFacade.getAll(color, operation, cottonPart);
    }

    @GetMapping("/history")
    public List<SocksTransactionDto> getHistory(@RequestParam
                                                @DateTimeFormat(pattern = DATE_TIME_FORMAT_PATTERN) LocalDateTime rangeStart,
                                                @RequestParam
                                                @DateTimeFormat(pattern = DATE_TIME_FORMAT_PATTERN) LocalDateTime rangeEnd) {
        log.info("History of receiving and issuing socks from the warehouse");
        return socksFacade.getHistory(rangeStart, rangeEnd);
    }

}
