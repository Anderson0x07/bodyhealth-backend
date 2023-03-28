package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.Query;
import server.bodyhealth.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query(
            value = "SELECT * from pedido p WHERE p.id_compra = :id_compra ",
            nativeQuery=true
    )
    List<Pedido> findByCompra(int id_compra);
}
