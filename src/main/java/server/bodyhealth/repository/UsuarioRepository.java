package server.bodyhealth.repository;

import server.bodyhealth.entity.Administrador;
import server.bodyhealth.entity.Cliente;
import server.bodyhealth.entity.Entrenador;
import server.bodyhealth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    //Usuario findByEmail(String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'ADMIN' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolAdmin(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolCliente(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolTrainer(@Param("email") String email);


    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.estado=1",
            nativeQuery = true
    )
    List<Cliente> findClientesActivos();
    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.estado=0",
            nativeQuery = true
    )
    List<Cliente> findClientesDesactivados();

    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=1",
            nativeQuery = true
    )
    List<Entrenador> findEntrenadoresActivos();
    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=0",
            nativeQuery = true
    )
    List<Entrenador> findEntrenadoresDesactivados();


    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=1 and u.jornada=:jornada",
            nativeQuery = true
    )
    List<Entrenador> entrenadoresJornada(@Param("jornada") String jornada);


    @Query(
            value = "SELECT * from usuario u where u.rol = 'ADMIN' and u.email=:email",
            nativeQuery = true
    )
    Administrador encontrarAdminEmail(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.email=:email",
            nativeQuery = true
    )
    Entrenador encontrarTrainerEmail(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.email=:email",
            nativeQuery = true
    )
    Cliente encontrarClienteEmail(@Param("email") String email);

}
