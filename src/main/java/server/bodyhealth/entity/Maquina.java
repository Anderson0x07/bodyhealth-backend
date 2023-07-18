package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Data
@Table(name = "maquina")
public class Maquina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Positive
    private int  id_maquina;

    @NotEmpty(message = "Se requiere un nombre para la máquina")
    private  String nombre;

    @NotEmpty(message = "Se requiere un estado para la máquina")
    private String estado;

    @NotEmpty(message = "Se requiere una Observación para la máquina")
    private String observacion;


    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
}
