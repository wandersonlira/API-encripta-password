package com.lira.security.project.model.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.lira.security.project.model.entities.CommonUser;




public class UserDto extends RepresentationModel<UserDto> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long id_userDto;
	private String login;
	private String password;
	
	
	
	public UserDto() {}
	
	public UserDto(Long id, String login, String password) {
		super();
		this.id_userDto = id;
		this.login = login;
		this.password = password;
	}
	
	
	
	public UserDto entity(CommonUser userEntity) {
		return new UserDto(
				userEntity.getId(),
				userEntity.getLogin(),
				userEntity.getPassword()
				);
	}
	
	
	
	public Long getId() {
		return id_userDto;
	}
	public void setId(Long id) {
		this.id_userDto = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id_userDto, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(id_userDto, other.id_userDto) && Objects.equals(login, other.login);
	}
	
	
	

}
