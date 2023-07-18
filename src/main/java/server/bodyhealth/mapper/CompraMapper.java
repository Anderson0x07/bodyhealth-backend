package server.bodyhealth.mapper;

import org.mapstruct.Mapper;

import server.bodyhealth.dto.CompraDto;
import server.bodyhealth.entity.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    Compra toEntity(CompraDto compraDto);
    CompraDto toDto(Compra compra);
}
