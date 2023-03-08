package server.bodyhealth.implement;

import server.bodyhealth.entity.MetodoPago;
import server.bodyhealth.repository.MetodoPagoRepository;
import server.bodyhealth.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoImplement implements MetodoPagoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;
    @Override
    public List<MetodoPago> listarMetodosPagos() {
        return (List<MetodoPago>) metodoPagoRepository.findAll();
    }

    @Override
    public void guardar(MetodoPago metodoPago) {
        metodoPagoRepository.save(metodoPago);
    }

    @Override
    public void eliminar(MetodoPago metodoPago) {
        metodoPagoRepository.delete(metodoPago);
    }

    @Override
    public MetodoPago encontrarMetodoPago(MetodoPago metodoPago) {

        return metodoPagoRepository.findById(metodoPago.getId_metodopago()).orElse(null);
    }
}
