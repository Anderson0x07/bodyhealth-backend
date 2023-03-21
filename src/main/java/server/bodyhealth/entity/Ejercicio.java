package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "ejercicio")
public class Ejercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ejercicio;

    @ManyToOne
    @JoinColumn(name = "id_musculo")
    private Musculo musculo;

    @NotEmpty(message = "Se requiere una descripcion del ejercicio")
    private String descripcion;

    @Positive(message = "El numero de series debe ser mayor o igual a 1")
    private int series;

    @Positive(message = "El numero de repeticiones debe ser mayor o igual a 1")
    private int repeticiones;

    private String url_video;

    @JsonIgnoreProperties("ejercicio")
    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutinaEjercicio> rutinaEjercicios = new ArrayList<>();
}
