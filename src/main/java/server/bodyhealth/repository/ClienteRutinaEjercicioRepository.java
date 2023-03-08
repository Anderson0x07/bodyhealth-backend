package server.bodyhealth.repository;

import server.bodyhealth.entity.ClienteRutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRutinaEjercicioRepository extends JpaRepository<ClienteRutinaEjercicio,Integer> {

    @Query(
            value = "SELECT * from cliente_rutina_ejercicio c where c.id_cliente_rutina = :id_cliente_rutina ",
            nativeQuery = true
    )
    List<ClienteRutinaEjercicio> encontrarRutinaCompletaCliente(@Param("id_cliente_rutina") int id_cliente_rutina);

    /*@Query(
            value = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'bodyhealth' AND  TABLE_NAME = 'cliente_rutina_ejercicio'",
            nativeQuery = true
    )
    public abstract int idActual();*/

}
