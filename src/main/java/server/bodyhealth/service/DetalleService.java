package server.bodyhealth.service;

import server.bodyhealth.entity.Detalle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalleService {
    public List<Detalle> listarDetalles();

    public void guardar(Detalle detalle);

    public void eliminar(Detalle detalle);

    public Detalle encontrarDetalle(Detalle detalle);
}
