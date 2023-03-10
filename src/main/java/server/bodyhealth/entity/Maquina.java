package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "maquina")
public class Maquina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int  id_maquina;

    private  String nombre;

    private String estado;

    private String observacion;


    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
}
