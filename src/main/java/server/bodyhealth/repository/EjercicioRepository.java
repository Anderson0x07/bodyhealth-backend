package server.bodyhealth.repository;

import server.bodyhealth.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository extends JpaRepository<Ejercicio,Integer> {
}
