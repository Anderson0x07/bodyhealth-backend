package server.bodyhealth.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

    public static UserDetails build(Usuario user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(Usuario retrievedUser) {
        Rol rol = retrievedUser.getRol();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));

        return authorities;
    }
}
