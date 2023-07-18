package server.bodyhealth.repository;

import server.bodyhealth.dto.FacturasPlanesDto;
import server.bodyhealth.dto.FacturasPlanesProjection;
import server.bodyhealth.entity.ClienteDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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


    @Query("SELECT MONTH(cd.fecha_inicio) AS mes, COUNT(cd.id_factura) AS totalFacturas, SUM(p.precio) AS totalVentas " +
            "FROM ClienteDetalle cd INNER JOIN Plan p ON cd.plan.id_plan = p.id_plan " +
            "WHERE cd.fecha_inicio >= :dateResta " +
            "GROUP BY MONTH(cd.fecha_inicio) " +
            "ORDER BY MONTH(cd.fecha_inicio)")
    List<FacturasPlanesProjection> obtenerFacturasPlanesProjection(@Param("dateResta") Date dateResta);

    /*@Query("SELECT NEW server.bodyhealth.dto.FacturasPlanesDto(DATE_FORMAT(cd.fecha_inicio, '%M'), COUNT(cd.id_factura), SUM(p.precio)) " +
            "FROM ClienteDetalle cd INNER JOIN Plan p ON cd.id_factura = p.id_plan " +
            "WHERE cd.fecha_inicio >= :dateResta " +
            "GROUP BY MONTH(cd.fecha_inicio) " +
            "ORDER BY MONTH(cd.fecha_inicio)")
    List<FacturasPlanesDto> encontrarFacturasPorMes(@Param("dateResta") Date dateResta);*/




}
