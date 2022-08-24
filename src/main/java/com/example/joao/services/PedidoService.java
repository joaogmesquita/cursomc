package com.example.joao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.joao.domain.Pedido;
import com.example.joao.repositories.PedidoRepository;
import com.example.joao.services.exception.ObjectNotFoundExcepiton;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(()-> new ObjectNotFoundExcepiton("Objeto não encontrado! Id: "+ id + ", Tipo: "+ Pedido.class.getName()));

	}

}
