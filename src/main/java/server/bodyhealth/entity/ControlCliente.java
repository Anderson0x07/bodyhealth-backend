package server.bodyhealth.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "control_fisico")
public class ControlCliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_controlcliente;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;

    private double peso;

    private double estatura;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

}
