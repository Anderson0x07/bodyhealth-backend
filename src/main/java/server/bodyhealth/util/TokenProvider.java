package server.bodyhealth.util;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static server.bodyhealth.util.Constants.*;

@Service
public class TokenProvider {

	private TokenProvider() {
	}

	public static String generateToken(Authentication authentication) {
		// Genera el token con roles, issuer, fecha expiración (8h)
		final String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_TOKEN)
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
				.compact();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
			final UserDetails userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<SimpleGrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	public static String getUserName(final String token) {
		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		return claimsJws.getBody().getSubject();
	}

	public  boolean validateToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(SIGNING_KEY)
					.parseClaimsJws(token)
					.getBody();

			if (claims.getExpiration().before(new Date())) {
				return false; // Token ha expirado
			}

			return true;
		} catch (Exception e) {
			return false; // Token no es válido
		}
	}

}
