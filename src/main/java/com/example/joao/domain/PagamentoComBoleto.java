package com.example.joao.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.joao.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class PagamentoComBoleto extends Pagamento {
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
