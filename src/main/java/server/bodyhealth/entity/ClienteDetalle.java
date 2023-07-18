package server.bodyhealth.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "cliente_detalle")
public class ClienteDetalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private Plan plan;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(name="id_metodopago")
    private MetodoPago metodoPago;
}
