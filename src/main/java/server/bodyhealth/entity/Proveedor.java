package server.bodyhealth.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "id_proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maquina> maquinas = new ArrayList<>();
}
