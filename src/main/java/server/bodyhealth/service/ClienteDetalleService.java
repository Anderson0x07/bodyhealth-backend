package server.bodyhealth.service;

import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.dto.ClienteRutinaCompletoDto;
import server.bodyhealth.dto.ClienteRutinaDto;
import server.bodyhealth.entity.ClienteDetalle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteDetalleService {
    public List<ClienteDetalleDto> listarClientesDetalles();

    public int guardar(ClienteDetalleDto clienteDetalleDto);

    public void eliminar(int id_clienteDetalle);

    public ClienteDetalleDto editarClienteDetalle(int id, ClienteDetalleDto clienteDetalleDto);

    public ClienteDetalleDto encontrarClienteDetalle(int id_clienteDetalle);

}
