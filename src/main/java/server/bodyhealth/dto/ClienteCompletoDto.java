package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import server.bodyhealth.entity.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCompletoDto {

    private int id_usuario;

    @NotEmpty(message = "Se requiere el documento.")
    private int documento;

    @NotEmpty(message = "Se requiere el tipo de documento.")
    private String tipo_documento;

    @NotEmpty(message = "Se requiere el nombre.")
    private String nombre;

    @NotEmpty(message = "Se requiere el apellido.")
    private String apellido;

    private String telefono;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @NotEmpty(message = "Se requiere email.")
    private String email;

    @NotEmpty(message = "Se requiere el password.")
    private String password;

    private String foto;

    @NotEmpty(message = "Se requiere una jornada.")
    private String jornada;

    @NotEmpty(message = "Se requiere un comentario.")
    private String comentario;

    @NotEmpty(message = "Se requiere un estado")
    private boolean estado;

    private RolDto rol;

    private List<ClienteRutina> clienteRutinas = new ArrayList<>();

    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();

    private List<ControlCliente> controlClientes = new ArrayList<>();

    private List<EntrenadorCliente> clienteEntrenadores = new ArrayList<>();

    private List<Compra> compras = new ArrayList<>();
}
