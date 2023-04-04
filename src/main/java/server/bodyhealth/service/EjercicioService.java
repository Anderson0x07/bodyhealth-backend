package server.bodyhealth.service;

import server.bodyhealth.dto.EjercicioCompletoDto;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.entity.Ejercicio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EjercicioService {
    public List<EjercicioDto> listarEjercicios();

    public void guardar(EjercicioDto ejercicioDto);

    public void eliminar(int id_ejercicio);

    public EjercicioDto editarEjercicio(int id, EjercicioDto ejercicioDto);
    public EjercicioCompletoDto encontrarEjercicio(int id_ejercicio);
}
