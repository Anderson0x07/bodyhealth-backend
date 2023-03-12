package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    Proveedor toEntity(ProveedorDto proveedorDto);
    ProveedorDto toDto(Proveedor proveedor);
    @Mapping(target = "id_proveedor", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProveedorDto proveedorDto, @MappingTarget Proveedor proveedor);
}
