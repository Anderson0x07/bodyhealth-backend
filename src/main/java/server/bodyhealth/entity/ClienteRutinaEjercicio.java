package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cliente_rutina_ejercicio")
public class ClienteRutinaEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente_rutina_ejercicio;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente_rutina")
    private ClienteRutina id_cliente_rutina;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_rutina_ejercicio")
    private RutinaEjercicio id_rutina_ejercicio;
}
