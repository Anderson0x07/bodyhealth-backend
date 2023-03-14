package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.RutinaCompletaDto;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Rutina;

@Mapper(componentModel = "spring")
public interface RutinaCompletaMapper {
    Rutina toEntity(RutinaCompletaDto rutinaCompletaDto);
    RutinaCompletaDto toDto(Rutina rutina);
    @Mapping(target = "id_rutina", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RutinaCompletaDto rutinaCompletaDto, @MappingTarget Rutina rutina);
}
