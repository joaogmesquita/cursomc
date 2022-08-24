package com.example.joao.domain;

import javax.persistence.Entity;

import com.example.joao.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComCartao extends Pagamento {
	private Integer numeroParcela;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcela) {
		super(id, estado, pedido);
		this.numeroParcela = numeroParcela;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

}
