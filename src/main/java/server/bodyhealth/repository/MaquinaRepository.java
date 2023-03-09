package server.bodyhealth.repository;

import server.bodyhealth.entity.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina,Integer> {

    Maquina findById_maquina(Integer id_maquina);
}
