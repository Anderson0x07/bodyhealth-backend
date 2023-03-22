package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.CompraCompletoDto;
import server.bodyhealth.entity.Compra;

@Mapper(componentModel = "spring")
public interface CompraCompletoMapper {
    Compra toEntity(CompraCompletoDto compraCompletoDto);
    CompraCompletoDto toDto(Compra compra);
}
