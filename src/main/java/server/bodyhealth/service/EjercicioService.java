package server.bodyhealth.service;

import server.bodyhealth.entity.Ejercicio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EjercicioService {
    public List<Ejercicio> listarEjercicios();

    public void guardar(Ejercicio ejercicio);

    public void eliminar(Ejercicio ejercicio);

    public Ejercicio encontrarEjercicio(int id_ejercicio);
}
