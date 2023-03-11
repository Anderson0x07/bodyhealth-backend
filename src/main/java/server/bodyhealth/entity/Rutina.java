package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "rutina")
public class Rutina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rutina;

    @Column(length = 100)
    private String nombre_rutina;

    @Column(length = 100)
    private String descripcion;

//    @JsonIgnoreProperties("rutina")
//    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<RutinaEjercicio> rutinaEjercicios = new ArrayList<>();
//
//    @JsonIgnoreProperties("rutina")
//    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ClienteRutina> clienteRutinas = new ArrayList<>();

}
