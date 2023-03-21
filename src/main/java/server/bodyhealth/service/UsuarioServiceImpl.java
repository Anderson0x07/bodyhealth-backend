package server.bodyhealth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.mapper.UserDetailsMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UsuarioService {

	private RolRepository roleRepository;

	private UsuarioRepository userRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository userRepository, RolRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Usuario retrievedUser = userRepository.findByEmail(userName).get();
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}


//	@Override
//	public Usuario getUser(int id) {
//		return userRepository.findById_usuario(id);
//	}
//
//	@Override
//	public Usuario save(Usuario user) {
//		Rol userRole = roleRepository.findByNombre("USER");
//
//		Usuario userToSave = Usuario.builder().email(user.getEmail()).password(user.getPassword()).rol(userRole).build();
//
//		return userRepository.save(userToSave);
//	}
}
