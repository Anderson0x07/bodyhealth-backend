package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.bodyhealth.dto.FacturasProductosProjection;
import server.bodyhealth.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio,Integer> {



    @Query(
            value = "SELECT * from ejercicio e where e.id_musculo = :id_musculo",
            nativeQuery=true
    )
    List<Ejercicio> findAllByMusculo(@Param("id_musculo") int id);
}
