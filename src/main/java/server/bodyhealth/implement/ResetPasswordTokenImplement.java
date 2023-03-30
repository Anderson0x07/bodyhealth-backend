package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.entity.ResetPasswordToken;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.repository.ResetPasswordTokenRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.EmailService;
import server.bodyhealth.service.ResetPasswordTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class ResetPasswordTokenImplement implements ResetPasswordTokenService {

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private EmailService service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void generarTokenYEnviarEmail(Usuario usuario) throws Exception {
        try{
            ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
            resetPasswordToken.setUsuario(usuario);
            resetPasswordToken.setToken(UUID.randomUUID().toString());
            resetPasswordToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));
            resetPasswordTokenRepository.save(resetPasswordToken);
            service.enviarTokenPassword(usuario.getEmail(), usuario.getNombre(), resetPasswordToken.getToken());
        }catch (Exception e){
            throw new Exception("Ocurrió un error");
        }

    }

    @Override
    public void guardar(ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenRepository.save(resetPasswordToken);
    }

    @Override
    public void verificarToken(VerifyTokenRequestDto verifyTokenRequestDto) throws Exception {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(verifyTokenRequestDto.getToken());
        if (resetPasswordToken == null) {
            throw new Exception("Token invalido.");
        }
        if (resetPasswordToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new Exception("Token expirado.");
        }
        Usuario usuario = resetPasswordToken.getUsuario();
        if(bCryptPasswordEncoder.matches(usuario.getPassword(), verifyTokenRequestDto.getNewPassword())){
            throw new Exception("El password debe ser diferente al actual.");
        }
        usuario.setPassword(bCryptPasswordEncoder.encode(verifyTokenRequestDto.getNewPassword()));
        log.info(verifyTokenRequestDto.getNewPassword());
        usuarioRepository.save(usuario);
        resetPasswordTokenRepository.delete(resetPasswordToken);
    }
}
