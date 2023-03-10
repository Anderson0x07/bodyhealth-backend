package server.bodyhealth.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "detalle")
public class Detalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;

    private String plan;

    private double precio;
    private int meses;

    @JsonIgnoreProperties("detalle")
    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();
}
