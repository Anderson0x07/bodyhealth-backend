package server.bodyhealth.service;

import server.bodyhealth.entity.Compra;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompraService{
    public List<Compra> listarCompras();

    public void guardar(Compra compra);

    public void eliminar(Compra compra);

    public Compra encontrarCompra(Compra compra);
}
