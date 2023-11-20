package com.lira.security.project.model.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lira.security.project.model.entities.UserEntity;
import com.lira.security.project.model.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	
	
	public List<UserEntity> findAll(){
		return repository.findAll();
	}
	
	
	public UserEntity findById(Long id) {
		Optional<UserEntity> objUserEntity = repository.findById(id);
		return objUserEntity.get();
	}
	
	
	public UserEntity findByLogin(String login) {
		Optional<UserEntity> objUserEntity = repository.findByLogin(login);
		return objUserEntity.get();
	}
	
	
	public UserEntity insert(UserEntity userEntity) {
		return repository.save(userEntity);
	}
	

}
