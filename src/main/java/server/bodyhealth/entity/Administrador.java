package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@DiscriminatorValue(value="ADMIN")
public class Administrador extends Usuario implements Serializable{


}
