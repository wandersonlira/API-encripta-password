package com.lira.security.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lira.security.project.model.dto.UserDto;
import com.lira.security.project.model.entities.User;
import com.lira.security.project.model.entities.CommonUser;
import com.lira.security.project.model.servicies.UserService;


@RestController
@RequestMapping("/api/v1")
public class UserResource {
	
	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder encoderPassword;
	
	
	
	@GetMapping(value="/logs")
	public ResponseEntity<List<CommonUser>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value="/logs/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	
	
	@PostMapping(value="/insert")
	public ResponseEntity<CommonUser> insertUsers(@RequestBody CommonUser usuario){
		usuario.setPassword(encoderPassword.encode(usuario.getPassword()));
		return ResponseEntity.ok(service.insert(usuario));
	}
	
	
	@GetMapping(value="/validPassword")
	public ResponseEntity<Boolean> validPasswords(@RequestParam String login, @RequestParam String password) {
		
		Optional<CommonUser> objUserEntity = Optional.of(service.findByLogin(login));
		
		if (objUserEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		boolean valid = encoderPassword.matches(password, objUserEntity.get().getPassword());
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}
	
	
	@PostMapping(value="/userCredentials")
	public ResponseEntity<?> saveUserCredentials(@RequestBody CommonUser commonUser) {
	try {
		if (commonUser == null || commonUser.getLogin().isBlank() || commonUser.getLogin() == null ||
				commonUser.getPassword().isBlank() || commonUser.getPassword() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"Login e senha são obrigatórios.");
		}
		var user = buildUserFromEntity(commonUser);
		service.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso: " /*+ user.getUserName()*/);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao salvar credenciais do usuário: " + e.getMessage());
		}
	}
	
	
	private User buildUserFromEntity(CommonUser commonUser) {
	    User user = new User();
	    user.setUserName(commonUser.getLogin());
	    user.setPassword(encoderPassword.encode(commonUser.getPassword()));
	    user.setFullName(commonUser.getLogin().toUpperCase());
	    user.setAccountNonExpired(true);
	    user.setAccountNonLocked(true);
	    user.setCredentialsNonExpired(true);
	    user.setEnabled(true);
	    return user;
	}


}
