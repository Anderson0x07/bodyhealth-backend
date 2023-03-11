package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.bodyhealth.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan,Integer> {

//    @Query(
//            value = "SELECT * from detalle ",
//            nativeQuery=true
//    )
//    List<Detalle> listarActivos();
}
