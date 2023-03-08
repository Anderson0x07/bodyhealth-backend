package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "entrenador_cliente")
public class EntrenadorCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_asignacion;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
