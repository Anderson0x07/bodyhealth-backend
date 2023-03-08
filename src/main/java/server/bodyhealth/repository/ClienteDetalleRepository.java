package server.bodyhealth.repository;

import server.bodyhealth.entity.ClienteDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteDetalleRepository extends JpaRepository<ClienteDetalle,Integer> {
    @Query(
            value = "SELECT * from cliente_detalle cd where cd.id_cliente = :id_cliente and CURDATE() BETWEEN cd.fecha_inicio and cd.fecha_fin",
            nativeQuery=true
    )
    ClienteDetalle encontrarPlan(@Param("id_cliente") int id_cliente);

    @Query(
            value = "SELECT * from cliente_detalle cd where CURDATE() BETWEEN cd.fecha_inicio and cd.fecha_fin",
            nativeQuery=true
    )
    List<ClienteDetalle> encontrarPlanes();

    @Query(
            value = "SELECT * from cliente_detalle cd where cd.id_detalle = :id_plan ",
            nativeQuery=true
    )
    List<ClienteDetalle> encontrarClientePlan(@Param("id_plan") int id_plan);
}
