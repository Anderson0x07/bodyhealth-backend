package server.bodyhealth.repository;

import server.bodyhealth.entity.EntrenadorCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EntrenadorClienteRepository extends JpaRepository<EntrenadorCliente,Integer> {



    @Query(
            value = "SELECT * from entrenador_cliente d where d.id_cliente = :cliente",
            nativeQuery=true
    )
    EntrenadorCliente encontrarEntrenador(@Param("cliente") int id_cliente);

    @Query(
            value = "SELECT * from entrenador_cliente d where d.id_entrenador = :id_entrenador",
            nativeQuery = true
    )
    List<EntrenadorCliente> encontrarClientes(@Param("id_entrenador") int id_entrenador);


}
