package server.bodyhealth.repository;

import server.bodyhealth.entity.RutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaEjercicioRepository extends JpaRepository<server.bodyhealth.entity.RutinaEjercicio,Integer> {

    @Query(
            value = "SELECT * FROM rutina_ejercicio re where re.id_rutina = :id_rutina",
            nativeQuery=true
    )
    List<RutinaEjercicio> encontrarRutinaEjerciciosByRutina(@Param("id_rutina") int id_rutina);

}
