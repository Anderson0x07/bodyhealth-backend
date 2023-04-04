package server.bodyhealth.service;

import server.bodyhealth.dto.ControlClienteDto;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.entity.ControlCliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ControlClienteService{
    public List<ControlClienteDto> listarContolClientes();

    public void guardar(ControlClienteDto controlClienteDto);

    public void eliminar(int id);

    public ControlClienteDto encontrarControlCliente(int id_controlCliente);

    public ControlClienteDto editarControlCliente(int id, ControlClienteDto controlClienteDto);
}
