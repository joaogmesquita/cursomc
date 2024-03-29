package com.example.joao.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.joao.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "The name field is required")
	@Length(min = 5, max = 20, message = "Name must be between 5 and 20 characters")
	private String nome;
	@NotEmpty(message="The field email is required")
	@Email(message="Email invalid")
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente obj) {

		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
