package server.bodyhealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue(value="CLIENTE")
public class Cliente extends Usuario implements Serializable {

    private String foto;

    private String jornada;

    private String comentario;

    private boolean estado;

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteRutina> clienteRutinas = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ControlCliente> controlClientes = new ArrayList<>();

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntrenadorCliente> entrenadorClientes = new ArrayList<>();
}
