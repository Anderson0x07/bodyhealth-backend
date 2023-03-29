package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.bodyhealth.entity.InfoBasica;

public interface InfoBasicaRepository extends JpaRepository<InfoBasica,Integer> {
}
