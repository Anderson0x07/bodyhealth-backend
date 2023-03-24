package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private int id_usuario;

    @Positive
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

    private String comentario;

    private boolean estado;

    private RolDto rol;
}
