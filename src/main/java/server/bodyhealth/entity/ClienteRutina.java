package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cliente_rutina")
public class ClienteRutina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_clienterutina;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_rutina")
    private Rutina id_rutina;
}
