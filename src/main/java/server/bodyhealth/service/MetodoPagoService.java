package server.bodyhealth.service;

import server.bodyhealth.dto.MetodoPagoDto;
import server.bodyhealth.dto.PlanCompletoDto;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.entity.MetodoPago;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MetodoPagoService {
    public List<MetodoPagoDto> listarMetodosPago();

    public void guardar(MetodoPagoDto metodoPagoDto);

    public void eliminar(int id_metodoPago);

    public MetodoPagoDto editarMetodoPago(int id, MetodoPagoDto metodoPagoDto);

    public MetodoPagoDto encontrarMetodoPago(int id_metodoPago);

}
