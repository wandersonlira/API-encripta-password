package com.lira.security.project.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lira.security.project.model.entities.CommonUser;

@Repository
public interface UsuarioRepository extends JpaRepository<CommonUser, Long>{
	
	public Optional<CommonUser> findByLogin(String login);

}
