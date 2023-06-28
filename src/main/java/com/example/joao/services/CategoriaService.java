package com.example.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

		return obj.orElseThrow(() -> new ObjectNotFoundExcepiton(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.example.joao.services.exception.DataIntegrityViolationException(
					"Não é possivel apagar categoria com produtos");

		}

	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linerPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linerPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

}
