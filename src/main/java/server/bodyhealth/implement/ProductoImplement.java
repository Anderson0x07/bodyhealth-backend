package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.EntrenadorDto;
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
import server.bodyhealth.service.StorageService;
import server.bodyhealth.util.MessageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
@Slf4j
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
    private StorageService service;

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
        service.deleteFile(producto.getFoto());
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


    @Override
    public ProductoDto loadImage(ProductoDto productoDto) throws IOException {
        Producto producto = productoRepository.findById(productoDto.getId_producto()).get();
        if(!productoDto.getFoto().equals("") && !producto.getFoto().equals(productoDto.getFoto())) {
            String[] foto = productoDto.getFoto().split("\\s+");
            byte[] image1 = Base64.getMimeDecoder().decode(foto[0]);
            File file = convertBytesToFile(image1, foto[1]);
            String[] tipo = foto[1].split("\\.");
            String nombre = "PRODUCT_" + productoDto.getNombre() + "." + tipo[tipo.length - 1];
            if (file != null) {
                productoDto.setFoto(nombre);
                service.uploadFile(file, nombre);
            }
            file.delete();
        }
        return productoDto;
    }

    @Override
    public void desactivarProducto(int id) {
        Producto producto = productoRepository.findById(id).get();
        if(producto.isEstado()){
            producto.setEstado(false);
        }
        productoRepository.save(producto);
    }

    @Override
    public void activarProducto(int id) {
        Producto producto = productoRepository.findById(id).get();
        if(!producto.isEstado()){
            producto.setEstado(true);
        }
        productoRepository.save(producto);
    }

    public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        return file;
    }

}
