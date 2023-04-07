package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.MusculoCompletoDto;
import server.bodyhealth.entity.Musculo;

@Mapper(componentModel = "spring")
public interface MusculoCompletoMapper {

    Musculo toEntity(MusculoCompletoDto musculoCompletoDto);

    MusculoCompletoDto toDto(Musculo musculo);
}
