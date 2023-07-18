package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="password_token")
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String token;
    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;
    private LocalDateTime expiryDate;

    private LocalDateTime creacion;
    private boolean usado;
}
