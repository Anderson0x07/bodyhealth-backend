package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorDto {

    private int id_usuario;

    @Positive
    private int documento;

    @NotEmpty(message = "Se requiere el tipo de documento.")
    private String tipo_documento;

    @NotEmpty(message = "Se requiere el nombre.")
    private String nombre;

    @NotEmpty(message = "Se requiere un apellido.")
    private String apellido;

    private String telefono;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @NotEmpty(message = "Se requiere email.")
    private String email;

    @NotEmpty(message = "Se requiere el password.")
    private String password;

    @NotEmpty(message = "Se requiere una jornada.")
    private String jornada;

    private String foto;

    @NotEmpty(message = "Se requiere la experiencia.")
    private String experiencia;

    @NotEmpty(message = "Se requiere la hoja de vida.")
    private String hoja_vida;

    @NotEmpty(message = "Se requiere un titulo academico.")
    private String titulo_academico;

    private boolean estado;

    private RolDto rol;
}
