package server.bodyhealth.service;

import server.bodyhealth.entity.MetodoPago;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MetodoPagoService {
    public List<MetodoPago> listarMetodosPagos();

    public void guardar(MetodoPago metodoPago);

    public void eliminar(MetodoPago metodoPago);

    public MetodoPago encontrarMetodoPago(MetodoPago metodoPago);
}
