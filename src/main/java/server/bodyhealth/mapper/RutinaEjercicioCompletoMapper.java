package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.RutinaEjercicioCompletoDto;
import server.bodyhealth.entity.RutinaEjercicio;

@Mapper(componentModel = "spring")
public interface RutinaEjercicioCompletoMapper {
    RutinaEjercicio toEntity(RutinaEjercicioCompletoDto rutinaEjercicioCompletoDto);
    RutinaEjercicioCompletoDto toDto(RutinaEjercicio rutinaEjercicio);
}
