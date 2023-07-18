package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.bodyhealth.entity.Rol;

public interface RolRepository extends JpaRepository<Rol,Integer> {

    @Query(
            value = "SELECT * FROM rol u where u.id_rol = :id_rol",
            nativeQuery=true
    )
    Rol getRolById(int id_rol);

    Rol findByNombre(String name);
}
