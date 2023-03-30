package server.bodyhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.bodyhealth.entity.ResetPasswordToken;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken,Integer> {

    ResetPasswordToken findByToken(String token);
}
