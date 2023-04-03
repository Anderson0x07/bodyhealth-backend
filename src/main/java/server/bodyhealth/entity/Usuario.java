package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario implements Serializable{

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

    @Column(unique = true, length = 100,nullable = false)
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @Column(length = 250,nullable = false)
    @Length(min = 5, max = 64)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    private String foto;

    private String experiencia;

    private String hoja_vida;

    private String titulo_academico;

    private String jornada;

    private boolean estado;
    @Column(nullable = false)
    private boolean confirmado;

    private String comentario;

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ClienteRutina> clienteRutinas = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ControlCliente> controlClientes = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EntrenadorCliente> clienteEntrenadores = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Compra> compras = new ArrayList<>();


    @JsonIgnoreProperties("entrenador")
    @OneToMany(mappedBy = "entrenador", fetch = FetchType.EAGER)
    private List<EntrenadorCliente> entrenadorClientes = new ArrayList<>();


}
