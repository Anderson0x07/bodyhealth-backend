package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@DiscriminatorValue(value="TRAINER")
public class Entrenador extends Usuario implements Serializable {

    private String foto;

    private String experiencia;

    private String hoja_vida;

    private String titulo_academico;

    private String jornada;

    private boolean estado;


    @JsonIgnoreProperties("entrenador")
    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntrenadorCliente> entrenadorClientes = new ArrayList<>();

    /*@ManyToOne
    @JoinColumn(name="id")
    private Usuario id_admin;*/



}