package com.example.joao;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.joao.domain.Categoria;
import com.example.joao.domain.Cidade;
import com.example.joao.domain.Cliente;
import com.example.joao.domain.Endereco;
import com.example.joao.domain.Estado;
import com.example.joao.domain.Pagamento;
import com.example.joao.domain.PagamentoComBoleto;
import com.example.joao.domain.PagamentoComCartao;
import com.example.joao.domain.Pedido;
import com.example.joao.domain.Produto;
import com.example.joao.domain.enums.EstadoPagamento;
import com.example.joao.domain.enums.TipoCliente;
import com.example.joao.repositories.CategoriaRepository;
import com.example.joao.repositories.CidadeRepository;
import com.example.joao.repositories.ClienteRepository;
import com.example.joao.repositories.EnderecoRepository;
import com.example.joao.repositories.EstadoRepository;
import com.example.joao.repositories.PagamentoRepository;
import com.example.joao.repositories.PedidoRepository;
import com.example.joao.repositories.ProdutoRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository rep;
	@Autowired
	private ProdutoRepository prod;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

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

		rep.saveAll(Arrays.asList(cat1, cat2));
		prod.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Maranhão");
		Estado e2 = new Estado(null, "Pará");

		Cidade c1 = new Cidade(null, "Arame", e1);
		Cidade c2 = new Cidade(null, "Arari", e1);
		Cidade c4 = new Cidade(null, "Belém", e2);

		e1.getCidades().addAll(Arrays.asList(c1, c2));
		e2.getCidades().addAll(Arrays.asList(c4));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c4));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("40028944", "080077780"));

		Endereco en1 = new Endereco(null, "Rua das Flores", "300", "Ap 203", "Jardim", "00000111", cli1, c1);
		Endereco en2 = new Endereco(null, "Rua dos Ratos", "30", "Ap 03", "Morro do Urubu", "352767537", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(en1, en2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(en1, en2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, en2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);

		ped2.setPagamento(pagto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}

}
