package am.dproc.sms.services.impl;

import io.jsonwebtoken.Claims;

public class JwtUtil {
	private String SECRET_KEY = "secret";
	
	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
		return null;
	}
	
	
}
