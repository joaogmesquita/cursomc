package com.example.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.joao.domain.Cidade;
import com.example.joao.domain.Cliente;
import com.example.joao.domain.Endereco;
import com.example.joao.domain.enums.TipoCliente;
import com.example.joao.domain.Cliente;
import com.example.joao.dto.ClienteDTO;
import com.example.joao.dto.ClienteNewDTO;
import com.example.joao.repositories.ClienteRepository;
import com.example.joao.repositories.EnderecoRepository;
import com.example.joao.services.exception.ObjectNotFoundExcepiton;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository endRepo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundExcepiton(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}

	@Transactional
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public void delete(Cliente obj) {
		Cliente newObj = find(obj.getId());

		try {
			repo.deleteById(newObj.getId());
		} catch (DataIntegrityViolationException e) {
			throw new com.example.joao.services.exception.DataIntegrityViolationException(
					"Não é possivel excluir pois há pedidos relacionadas");

		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromDTO(ClienteDTO DTO) {
		return new Cliente(DTO.getId(), DTO.getNome(), DTO.getEmail(), null, null);

	}

	public Cliente fromDTO(ClienteNewDTO DTO) {
		Cliente cli = new Cliente(null, DTO.getNome(), DTO.getEmail(), DTO.getCpfCnpfj(),
				TipoCliente.toEnum(DTO.getTipo()));
		Endereco end = new Endereco(null, DTO.getLogradouro(), DTO.getNumero(), DTO.getComplemento(), DTO.getBairro(),
				DTO.getCep(), cli, null);
		Cidade cid = new Cidade(DTO.getCidadeId(), null, null);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(DTO.getTelefone1());
		if (DTO.getTelefone2() != null) {
			cli.getTelefones().add(DTO.getTelefone2());
		}
		if (DTO.getTelefone3() != null) {
			cli.getTelefones().add(DTO.getTelefone3());
		}
		return cli;
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);

	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
