package com.lira.security.project.model.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lira.security.project.model.dto.security.AccountCredentialsVO;
import com.lira.security.project.model.dto.security.TokenVO;
import com.lira.security.project.model.repositories.UserRepository;
import com.lira.security.project.security.Jwt.JwtTokenProvider;


@Service
public class AuthServices {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repository.findByUsername(username);
			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}
			return ResponseEntity.ok(tokenResponse);
			
		} catch (BadCredentialsException e) {
		    System.err.println("Autenticação falhou: " + e.getMessage());
		    throw new BadCredentialsException("Invalid username/password supplied!");
		    
		    } catch (Exception e) {
		    	throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);
		
		var tokenResponse = new TokenVO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return ResponseEntity.ok(tokenResponse);
	}
}
