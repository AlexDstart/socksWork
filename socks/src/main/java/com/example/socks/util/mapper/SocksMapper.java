package com.example.socks.util.mapper;

import com.example.socks.model.dto.SocksDto;
import com.example.socks.model.entity.Socks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SocksMapper {

    @Mapping(target = "id", source = "socks.id")
    @Mapping(target = "color", source = "socks.color")
    @Mapping(target = "cottonPart", source = "socks.cottonPart")
    @Mapping(target = "quantity", source = "quantity")
    SocksDto socksToSocksDto(Socks socks, Integer quantity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "cottonPart", source = "cottonPart")
    Socks socksDtoToSocks(SocksDto socksDto);

}
