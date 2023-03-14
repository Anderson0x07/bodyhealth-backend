package server.bodyhealth.implement;

import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ProductoMapper;
import server.bodyhealth.repository.ProductoRepository;
import server.bodyhealth.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductoImplement implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ProductoMapper productoMapper;
    @Override
    public List<ProductoDto> listarProductos() {
        List<ProductoDto> productoDtos = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();
        if(!productos.isEmpty()) {
            for (Producto producto : productos
            ) {
                ProductoDto productoDto = productoMapper.toDto(producto);
                productoDtos.add(productoDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("productosEmpty",null, Locale.getDefault()));
        }
        return productoDtos;
    }

    @Override
    public void guardar(ProductoDto productoDto) {
        Producto producto = productoMapper.toEntity(productoDto);
        productoRepository.save(producto);
    }

    @Override
    public void eliminar(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    public Producto encontrarProducto(int  id_producto) {
        return productoRepository.findById(id_producto).orElse(null);
    }

    @Override
    public List<Producto> listarActivos() {
        return (List<Producto>) productoRepository.findByEstado(true);
    }

    @Override
    public List<Producto> listarDesactivados() {
        return (List<Producto>) productoRepository.findByEstado(false);
    }
}
