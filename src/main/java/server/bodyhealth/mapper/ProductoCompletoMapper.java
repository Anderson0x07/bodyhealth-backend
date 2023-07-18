package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.entity.Producto;

@Mapper(componentModel = "spring")
public interface ProductoCompletoMapper {
    Producto toEntity(ProductoCompletoDto productoCompletoDto);
    ProductoCompletoDto toDto(Producto producto);
}
