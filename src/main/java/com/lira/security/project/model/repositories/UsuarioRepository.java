package com.lira.security.project.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lira.security.project.model.entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	public Optional<UserEntity> findByLogin(String login);

}
