package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.entity.ResetPasswordToken;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.repository.ResetPasswordTokenRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.EmailService;
import server.bodyhealth.service.ResetPasswordTokenService;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
            //Queda pendiente solucionar lo de la zona horaria.
            ZoneId colombiaZoneId = ZoneId.of("America/Bogota");
            resetPasswordToken.setExpiryDate(LocalDateTime.now(colombiaZoneId).plusMinutes(15));
            resetPasswordToken.setCreacion(LocalDateTime.now(colombiaZoneId));
            resetPasswordToken.setUsado(false);
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
            throw new NotFoundException("Token invalido.");
        }
        if (resetPasswordToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new NotFoundException("Token expirado.");
        }
        Usuario usuario = resetPasswordToken.getUsuario();
        if(bCryptPasswordEncoder.matches(verifyTokenRequestDto.getNewPassword(),usuario.getPassword())){
            throw new NotFoundException("El password debe ser diferente al actual.");
        }
        if(resetPasswordToken.isUsado()){
            throw new NotFoundException("El token ya se uso anteriormente.");
        }
        usuario.setPassword(bCryptPasswordEncoder.encode(verifyTokenRequestDto.getNewPassword()));
        log.info(verifyTokenRequestDto.getNewPassword());
        usuarioRepository.save(usuario);
        resetPasswordToken.setUsado(true);
        resetPasswordTokenRepository.save(resetPasswordToken);
        service.enviarPasswordChanged(usuario.getEmail(), usuario.getNombre());
        //resetPasswordTokenRepository.delete(resetPasswordToken);
    }
}
