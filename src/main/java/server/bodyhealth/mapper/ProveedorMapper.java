package server.bodyhealth.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    Proveedor toEntity(ProveedorDto proveedorDto);
    ProveedorDto toDto(Proveedor proveedor);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProveedorDto proveedorDto, @MappingTarget Proveedor proveedor);
}
