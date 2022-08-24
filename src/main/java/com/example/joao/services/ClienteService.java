package com.example.joao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.joao.domain.Cliente;
import com.example.joao.repositories.ClienteRepository;
import com.example.joao.services.exception.ObjectNotFoundExcepiton;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(()-> new ObjectNotFoundExcepiton("Objeto não encontrado! Id: "+ id + ", Tipo: "+ Cliente.class.getName()));

	}

}
