package server.bodyhealth.repository;


import org.springframework.data.jpa.repository.Query;
import server.bodyhealth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByDocumento(int documento);


    @Query(
        value = "SELECT * FROM usuario u where u.id_usuario = :id_usuario",
        nativeQuery=true
    )
    Usuario findById_usuario(int id_usuario);


    //Usuario findById_usuario(int id);

    List<Usuario> findAll();

    @Query(
            value = "SELECT * FROM usuario u where u.id_rol = :id_rol",
            nativeQuery=true
    )
    List<Usuario> findAllByRol(int id_rol);


}
