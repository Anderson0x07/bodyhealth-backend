package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ProductoCompletoMapper;
import server.bodyhealth.mapper.ProductoMapper;
import server.bodyhealth.repository.ProductoRepository;
import server.bodyhealth.repository.ProveedorRepository;
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

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoCompletoMapper productoCompletoMapper;
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

    @Transactional
    @Override
    public void guardar(ProductoDto productoDto) {
        Producto producto = productoMapper.toEntity(productoDto);
        productoRepository.save(producto);
    }

    @Override
    public void eliminar(int id_producto) {
        Producto producto = productoRepository.findById(id_producto).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("productoNotFound",null, Locale.getDefault()))
        );
        productoRepository.deleteById(id_producto);
    }

    @Override
    public ProductoCompletoDto encontrarProducto(int  id_producto) {
        return productoCompletoMapper.toDto(productoRepository.findById(id_producto).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("productoNotFound",null, Locale.getDefault()))
        ));
    }

    @Transactional
    @Override
    public void editarProveedor(int id, ProductoDto productoDto) {

        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("productoNotFound",null, Locale.getDefault()))
        );
        producto.setEstado(productoDto.isEstado());
        if(productoDto.getNombre()!=null)
            producto.setNombre(productoDto.getNombre());
        if(productoDto.getFoto()!=null)
            producto.setFoto(productoDto.getFoto());
        if(productoDto.getPrecio()>=100.0)
            producto.setPrecio(productoDto.getPrecio());
        if(productoDto.getStock()>=0)
            producto.setStock(productoDto.getStock());
        Proveedor proveedor = proveedorRepository.findById(productoDto.getProveedor().getId_proveedor()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("proveedorNotFound",null, Locale.getDefault()))
        );
        if(productoDto.getProveedor()!=null)
            producto.setProveedor(proveedor);
        productoRepository.save(producto);
    }

}
