package server.bodyhealth.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.entity.ResetPasswordToken;
import server.bodyhealth.entity.Usuario;

@Service
public interface ResetPasswordTokenService {
    public void generarTokenYEnviarEmail(Usuario usuario) throws Exception;

    public void guardar(ResetPasswordToken resetPasswordToken);

    public void verificarToken(VerifyTokenRequestDto verifyTokenRequestDto) throws Exception;
}
