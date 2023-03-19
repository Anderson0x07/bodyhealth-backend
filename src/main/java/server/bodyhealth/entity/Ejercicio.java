package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
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

    private String descripcion;
    private String series;

    private int repeticiones;


    private String url_video;

    @JsonIgnoreProperties("ejercicio")
    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutinaEjercicio> rutinaEjercicios = new ArrayList<>();
}
