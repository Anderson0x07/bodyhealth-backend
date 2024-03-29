package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Se requiere el nombre del musculo")
    private String nombre;

    @NotEmpty(message = "Se requiere el grupo muscular al que pertenece")
    private String grupo_muscular;

    private String descripcion;

    @JsonIgnoreProperties("musculo")
    @OneToMany(mappedBy = "musculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejercicio> ejercicios = new ArrayList<>();
}
