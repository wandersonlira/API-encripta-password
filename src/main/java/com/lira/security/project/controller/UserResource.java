package com.lira.security.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lira.security.project.model.entities.UserEntity;
import com.lira.security.project.model.servicies.UserService;


@RestController
@RequestMapping("/api/user")
public class UserResource {
	
	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder encoderPassword;
	
	
	
	@GetMapping(value="/logs")
	public ResponseEntity<List<UserEntity>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	
	@PostMapping(value="/insert")
	public ResponseEntity<UserEntity> insertUsers(@RequestBody UserEntity usuario){
		usuario.setPassword(encoderPassword.encode(usuario.getPassword()));
		return ResponseEntity.ok(service.insert(usuario));
	}
	
	
	@GetMapping(value="/validPassword")
	public ResponseEntity<Boolean> validPasswords(@RequestParam String login, @RequestParam String password) {
		
		Optional<UserEntity> objUserEntity = Optional.of(service.findByLogin(login));
		
		if (objUserEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		boolean valid = encoderPassword.matches(password, objUserEntity.get().getPassword());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}

}
