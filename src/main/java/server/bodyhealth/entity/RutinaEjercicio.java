package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "rutina_ejercicio")
public class RutinaEjercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rutina_ejercicio;

    @ManyToOne
    @JoinColumn(name = "id_rutina")
    private Rutina rutina;

    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;

    @JsonIgnoreProperties("rutinaEjercicio")
    @OneToMany(mappedBy = "rutinaEjercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteRutinaEjercicio> clienteRutinaEjercicios = new ArrayList<>();
}
