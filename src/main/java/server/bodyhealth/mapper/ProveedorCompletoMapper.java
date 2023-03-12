package server.bodyhealth.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import server.bodyhealth.dto.ProveedorCompletoDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;

@Mapper(componentModel = "spring")
public interface ProveedorCompletoMapper {
    Proveedor toEntity(ProveedorCompletoDto proveedorCompletoDto);
    ProveedorCompletoDto toDto(Proveedor proveedor);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProveedorCompletoDto proveedorCompletoDto, @MappingTarget Proveedor proveedor);
}
