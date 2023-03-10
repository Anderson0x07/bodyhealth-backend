package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "musculo")
public class Musculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_musculo;

    private String descripcion;

    @JsonIgnoreProperties("musculo")
    @OneToMany(mappedBy = "musculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejercicio> ejercicios = new ArrayList<>();
}
