package server.bodyhealth.service;

import server.bodyhealth.dto.MetodoPagoCompletoDto;
import server.bodyhealth.dto.MetodoPagoDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MetodoPagoService {
    public List<MetodoPagoCompletoDto> listarMetodosPago();

    public void guardar(MetodoPagoDto metodoPagoDto);

    public void eliminar(int id_metodoPago);

    public MetodoPagoDto editarMetodoPago(int id, MetodoPagoDto metodoPagoDto);

    public MetodoPagoDto encontrarMetodoPago(int id_metodoPago);

}
