package server.bodyhealth.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Se require el nombre")
    @Column(length = 50)
    private String nombre_empresa;

    @NotEmpty(message = "Se require el telefono")
    @Column(length = 13)
    private String telefono;

    @NotEmpty(message = "Se requiere la dirección")
    @Column(length = 80)
    private String direccion;


    @JsonIgnoreProperties("proveedor")
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maquina> maquinas = new ArrayList<>();


    @JsonIgnoreProperties("proveedor")
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();
}
