package server.bodyhealth.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "compra")
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_compra;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="id_cliente")
    private Cliente id_cliente;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="id_metodopago")
    private MetodoPago id_metodopago;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_compra;

    private double total;
}
