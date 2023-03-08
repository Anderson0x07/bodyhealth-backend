package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;


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



    /*@ManyToOne
    @JoinColumn(name="id")
    private Usuario id_admin;*/



}