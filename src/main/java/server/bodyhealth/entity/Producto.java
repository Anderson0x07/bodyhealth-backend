package server.bodyhealth.entity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    @Column(length = 100)
    private String nombre;

    @Column(length = 150)
    private int stock;

    @Column(length = 150)
    private double precio;

    @Column(length = 150)
    private  String foto;

    private boolean estado;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_proveedor")
    private Proveedor id_proveedor;
}
