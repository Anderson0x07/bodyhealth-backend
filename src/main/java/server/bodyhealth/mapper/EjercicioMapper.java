package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.entity.Ejercicio;

@Mapper(componentModel = "spring")
public interface EjercicioMapper {
    Ejercicio toEntity(EjercicioDto ejercicioDto);
    EjercicioDto toDto(Ejercicio ejercicio);
}
