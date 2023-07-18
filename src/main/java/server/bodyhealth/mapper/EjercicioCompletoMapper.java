package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.EjercicioCompletoDto;
import server.bodyhealth.entity.Ejercicio;

@Mapper(componentModel = "spring")
public interface EjercicioCompletoMapper {
    Ejercicio toEntity(EjercicioCompletoDto ejercicioCompletoDto);
    EjercicioCompletoDto toDto(Ejercicio ejercicio);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateEntity(EjercicioCompletoDto ejercicioCompletoDto, @MappingTarget Ejercicio ejercicio);
}
