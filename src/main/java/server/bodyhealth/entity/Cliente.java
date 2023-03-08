package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@DiscriminatorValue(value="CLIENTE")
public class Cliente extends Usuario implements Serializable {

    private String foto;

    private String jornada;

    private String comentario;

    private boolean estado;
}
