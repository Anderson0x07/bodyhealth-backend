package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.MusculoDto;
import server.bodyhealth.entity.Musculo;

@Mapper(componentModel = "spring")
public interface MusculoMapper {

    Musculo toEntity(MusculoDto musculoDto);

    MusculoDto toDto(Musculo musculo);

    @Mapping(target = "id_musculo", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(MusculoDto musculoDto, @MappingTarget Musculo musculo);
}
