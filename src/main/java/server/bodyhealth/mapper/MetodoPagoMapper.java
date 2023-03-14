package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.MetodoPagoDto;
import server.bodyhealth.entity.MetodoPago;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {

    MetodoPago toEntity(MetodoPagoDto metodoPagoDto);

    MetodoPagoDto toDto(MetodoPago metodoPago);

    @Mapping(target = "id_metodopago", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(MetodoPagoDto metodoPagoDto, @MappingTarget MetodoPago metodoPago);
}
