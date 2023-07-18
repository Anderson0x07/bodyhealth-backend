package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.bodyhealth.entity.InfoBasica;

public interface InfoBasicaRepository extends JpaRepository<InfoBasica,Integer> {

    @Query(
            value = "SELECT logo_empresa from info_basica i where i.id_configuracion = :id",
            nativeQuery=true
    )
    String findLogoById(int id);
}
