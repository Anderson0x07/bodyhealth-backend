package server.bodyhealth.service;

import server.bodyhealth.dto.CompraCompletoDto;
import server.bodyhealth.dto.CompraDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Compra;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompraService{
    public List<CompraDto> listarCompras();

    public void guardar(CompraDto compraDto);

    public void eliminar(int id);

    public CompraCompletoDto encontrarCompra(int id_compra);

    public void editarProveedor(int id, CompraDto compraDto);
}
