package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.Query;
import server.bodyhealth.entity.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaquinaRepository extends JpaRepository<Maquina,Integer> {

    @Query(
            value = "SELECT * from maquina m where m.id_maquina = :id_maquina",
            nativeQuery = true
    )
    Optional<Maquina> findMaquinaById_maquina(Integer id_maquina);
}
