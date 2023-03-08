package server.bodyhealth.repository;

import server.bodyhealth.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
