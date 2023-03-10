package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cliente_rutina")
public class ClienteRutina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_clienterutina;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_rutina")
    private Rutina rutina;

    @JsonIgnoreProperties("clienteRutina")
    @OneToMany(mappedBy = "clienteRutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteRutinaEjercicio> clienteRutinaEjercicios = new ArrayList<>();
}
