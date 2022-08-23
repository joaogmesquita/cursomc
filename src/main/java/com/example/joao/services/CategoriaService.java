package com.example.joao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.joao.domain.Categoria;
import com.example.joao.repositories.CategoriaRepository;
import com.example.joao.services.exception.ObjectNotFoundExcepiton;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(()-> new ObjectNotFoundExcepiton("Objeto não encontrado! Id: "+ id + ", Tipo: "+ Categoria.class.getName()));

	}

}
