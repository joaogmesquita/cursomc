package com.example.joao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.joao.domain.Categoria;
import com.example.joao.domain.Cidade;
import com.example.joao.domain.Estado;
import com.example.joao.domain.Produto;
import com.example.joao.repositories.CategoriaRepository;
import com.example.joao.repositories.CidadeRepository;
import com.example.joao.repositories.EstadoRepository;
import com.example.joao.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository rep;
	@Autowired
	private ProdutoRepository prod;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Office");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado e1 = new Estado(null,"Maranhão");
		Estado e2 = new Estado(null,"Pará");
		
		Cidade c1 = new Cidade(null,"Arame", e1);
		Cidade c2 = new Cidade(null,"Arari", e1);
		Cidade c3 = new Cidade(null,"Penalva", e1);
		Cidade c4 = new Cidade(null,"Belém", e2);
		Cidade c5 = new Cidade(null,"algo do pará", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1,c2,c3));
		e2.getCidades().addAll(Arrays.asList(c4,c5));
		
		
		

		rep.saveAll(Arrays.asList(cat1, cat2));
		prod.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		

	}

}
