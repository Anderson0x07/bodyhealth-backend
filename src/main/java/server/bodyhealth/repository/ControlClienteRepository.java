package server.bodyhealth.repository;

import server.bodyhealth.entity.ControlCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ControlClienteRepository extends JpaRepository<ControlCliente,Integer> {

    @Query(
            value = "SELECT * from control_fisico c where c.id_cliente = :id_cliente ORDER BY c.fecha DESC limit 1",
            nativeQuery = true
    )
    ControlCliente encontrarControlCliente(@Param("id_cliente") int id_cliente);

}
