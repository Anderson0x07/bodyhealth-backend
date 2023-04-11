package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.RutinaCompletaDto;
import server.bodyhealth.dto.RutinaCompletaSinClienteDto;
import server.bodyhealth.entity.Rutina;

@Mapper(componentModel = "spring")
public interface RutinaCompletaSinClienteMapper {

    Rutina toEntity(RutinaCompletaSinClienteDto rutinaCompletaSinClienteDto);

    RutinaCompletaSinClienteDto toDto(Rutina rutina);
}
