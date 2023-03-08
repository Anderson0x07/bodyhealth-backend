package server.bodyhealth.entity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="proveedor")
public class Proveedor implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;

    @Column(length = 50)
    private String nombre_empresa;

    @Column(length = 13)
    private String telefono;

    @Column(length = 80)
    private String direccion;
}
