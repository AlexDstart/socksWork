package com.example.socks.util.mapper;

import com.example.socks.model.dto.SocksTransactionDto;
import com.example.socks.model.entity.SocksTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SocksTransactionMapper {
    @Mapping(target = "color", source = "socksTransaction.socks.color")
    @Mapping(target = "cottonPart", source = "socksTransaction.socks.cottonPart")
    @Mapping(target = "operationType", source = "operationType")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "transactionDate", source = "transactionDate")
    SocksTransactionDto socksTransactionToSocksTransactionDto(SocksTransaction socksTransaction);
}
