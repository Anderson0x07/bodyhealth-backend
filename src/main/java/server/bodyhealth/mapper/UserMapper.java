package server.bodyhealth.mapper;


import server.bodyhealth.entity.Usuario;
import server.bodyhealth.presentation.UserResponse;
import server.bodyhealth.presentation.AuthorizationRequest;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Usuario user) {
		return UserResponse.builder().name(user.getEmail()).id(user.getId_usuario()).build();
	}

	public static Usuario toDomain(AuthorizationRequest authorizationRequest) {
		return Usuario.builder().email(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
				.build();
	}
}
