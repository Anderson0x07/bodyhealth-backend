package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCompletoDto {

    private int id_usuario;

    private int documento;

    private String tipo_documento;

    private String nombre;

    private String apellido;

    private String telefono;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    private String email;

    private String password;

    private String foto;

    private String jornada;

    private String comentario;

    private boolean estado;

    private RolDto rol;

    private boolean confirmado;

    private String experiencia;

    private String hoja_vida;

    private String titulo_academico;

    private List<ClienteRutinaSinClienteDto> clienteRutinas = new ArrayList<>();

    private List<ClienteDetalleSinClienteDto> clienteDetalles = new ArrayList<>();

    private List<ControlClienteSinClienteDto> controlClientes = new ArrayList<>();

    private List<EntrenadorClienteSinClienteDto> clienteEntrenadores = new ArrayList<>();

    private List<CompraSinClienteDto> compras = new ArrayList<>();

    private List<EntrenadorClienteSinEntrenadorDto> entrenadorClientes = new ArrayList<>();
}
