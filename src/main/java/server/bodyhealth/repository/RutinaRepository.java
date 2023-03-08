package server.bodyhealth.repository;

import server.bodyhealth.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutinaRepository extends JpaRepository<Rutina,Integer> {

    /* NO NECESARIO
    @Query(
            value = "SELECT * FROM rutina r where r.id_rutina = :id_rutina",
            nativeQuery=true
    )
    Rutina encontrarRutinaDeCliente(@Param("id_rutina") int id_rutina);*/
}
