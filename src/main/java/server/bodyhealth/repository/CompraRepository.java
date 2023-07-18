package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.bodyhealth.dto.FacturasProductosProjection;
import server.bodyhealth.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra,Integer> {

    @Query("SELECT MONTH(c.fecha_compra) AS mes, SUM(c.total) AS totalVentas " +
            "FROM Compra c " +
            "WHERE c.fecha_compra >= :date " +
            "GROUP BY MONTH(c.fecha_compra) " +
            "ORDER BY MONTH(c.fecha_compra)")
    List<FacturasProductosProjection> obtenerFacturasProductosProjection(@Param("date") Date date);
}
