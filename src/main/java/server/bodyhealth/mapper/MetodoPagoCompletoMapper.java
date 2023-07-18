package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.MetodoPagoCompletoDto;
import server.bodyhealth.entity.MetodoPago;

@Mapper(componentModel = "spring")
public interface MetodoPagoCompletoMapper {

    MetodoPago toEntity(MetodoPagoCompletoDto metodoPagoCompletoDto);

    MetodoPagoCompletoDto toDto(MetodoPago metodoPago);

}
