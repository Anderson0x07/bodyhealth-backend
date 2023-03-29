package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.InfoBasicaDto;
import server.bodyhealth.entity.InfoBasica;

@Mapper(componentModel = "spring")
public interface InfoBasicaMapper {

    InfoBasica toEntity(InfoBasicaDto infoBasicaDto);

    InfoBasicaDto toDto(InfoBasica infoBasica);

    @Mapping(target = "id_configuracion", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(InfoBasicaDto infoBasicaDto, @MappingTarget InfoBasica infoBasica);

}
