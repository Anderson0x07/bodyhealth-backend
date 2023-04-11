package server.bodyhealth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.UsuarioCompletoDto;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.UserDetailsMapper;
import server.bodyhealth.mapper.UsuarioCompletoMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.util.MessageUtil;

import java.util.Locale;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	private RolRepository roleRepository;

	private UsuarioRepository userRepository;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private UsuarioCompletoMapper usuarioCompletoMapper;

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


	@Override
	public Usuario obtenerUsuarioLogueado(int id_usuario) {
		return userRepository.findById(id_usuario).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()))
		);

	}

	@Override
	public UsuarioCompletoDto obtenerUsuarioByEmail(String email) {
		return usuarioCompletoMapper.toDto(userRepository.findByEmail(email).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("usuarioNotFound", null, Locale.getDefault()))
		));
	}
}