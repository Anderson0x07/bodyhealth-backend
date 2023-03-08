package server.bodyhealth.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rol", discriminatorType = DiscriminatorType.STRING)
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(length=15, unique = true)
    private int documento;

    @Column(length = 3)
    private String tipo_documento;

    @Column(length = 40)
    private String nombre;

    @Column(length = 40)
    private String apellido;

    @Column(length = 13)
    private String telefono;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_nacimiento;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 250)
    private String password;


}
