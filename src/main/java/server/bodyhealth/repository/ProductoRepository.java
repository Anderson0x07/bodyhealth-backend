package server.bodyhealth.repository;


import server.bodyhealth.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    @Query(
            value = "SELECT * from producto e where e.estado = :estado",
            nativeQuery=true
    )
    List<Producto> findByEstado(@Param("estado") boolean estado);
}
