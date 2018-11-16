package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO dao;

	// RETORNO DO RELATORIO EM FORMATO JSON
	@RequestMapping("/relatorio-produtos")
	public @ResponseBody Relatorio relatorio(@DateTimeFormat String data) {

		Relatorio relatorio = new Relatorio();

		if (data != null) {
			relatorio.setProdutos(dao.listarPorData(data));
		} else {
			relatorio.setProdutos(dao.listar());
		}

		relatorio.setQuantidade(relatorio.getProdutos().size());

		return relatorio;
	}

}

// CLASSE PARA DEFINIR O FORMATO DO OBJETO QUE SERÁ RETORNADO EM FORMATO JSON
class Relatorio {

	@DateTimeFormat
	private Date dataGeracao = Calendar.getInstance().getTime();

	private int quantidade = 0;
	protected List<Produto> produtos = new ArrayList<>();

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
