package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.RutinaEjercicioDto;
import server.bodyhealth.entity.RutinaEjercicio;

@Mapper(componentModel = "spring")
public interface RutinaEjercicioMapper {
    RutinaEjercicio toEntity(RutinaEjercicioDto rutinaEjercicioDto);
    RutinaEjercicioDto toDto(RutinaEjercicio rutinaEjercicio);
}
