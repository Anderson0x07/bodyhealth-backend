package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_compra")
    private Compra compra;

    private int cantidad;

    private double total;

}
