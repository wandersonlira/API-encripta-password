package com.lira.security.project.model.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.lira.security.project.controller.UserResource;
import com.lira.security.project.model.dto.UserDto;
import com.lira.security.project.model.entities.User;
import com.lira.security.project.model.entities.CommonUser;
import com.lira.security.project.model.repositories.UserRepository;
import com.lira.security.project.model.repositories.UsuarioRepository;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UserRepository userRepository;


	public UserService(UsuarioRepository usuarioRepository, UserRepository userRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.userRepository = userRepository;
	}


	public List<CommonUser> findAll(){
		return usuarioRepository.findAll();
	}
	
	
	public UserDto findById(Long id) {
		 CommonUser objUserEntity = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID não encontrado!"));
		 UserDto userDto = new UserDto().entity(objUserEntity);
		return userDto.add(linkTo(methodOn(UserResource.class).findById(id)).withSelfRel());
	}
	
	
	public CommonUser findByLogin(String login) {
		Optional<CommonUser> objUserEntity = usuarioRepository.findByLogin(login);
		return objUserEntity.get();
	}
	
	
	public CommonUser insert(CommonUser userEntity) {
		return usuarioRepository.save(userEntity);
	}
	
	public User save(User user) {
		try {
			if (user == null || user.getUserName().isBlank() ||  user.getUserName() == null ||
					user.getPassword().isBlank() || user.getPassword() == null) {
//				throw new IllegalArgumentException("Login e senha são obrigatórios.");
			}
			
			return userRepository.save(user);
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Login e senha são obrigatórios."); 
		}
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + "not found");
		}
	}
	

}
