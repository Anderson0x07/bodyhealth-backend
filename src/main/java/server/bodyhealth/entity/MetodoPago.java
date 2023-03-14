package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "metodo_pago")
public class MetodoPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodopago;

    @NotEmpty(message = "Se requiere una descripción del metodo de pago")
    private String descripcion;

    @JsonIgnoreProperties("metodoPago")
    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();

    @JsonIgnoreProperties("metodoPago")
    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();

}
