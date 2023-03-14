package server.bodyhealth.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "plan")
public class Plan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_plan;

    @NotEmpty(message = "Se requiere nombre de plan")
    private String plan;

    @Positive(message = "El precio debe ser double")
    private double precio;
    @Positive(message = "El precio debe ser int")
    private int meses;

    @JsonIgnoreProperties("plan")
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();
}
