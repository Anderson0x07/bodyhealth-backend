package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

}
