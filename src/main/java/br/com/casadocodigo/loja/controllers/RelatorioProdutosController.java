package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@ResponseBody
	public List<Produto> relatorio(String data){
				
		if(data != null) {
			return dao.listarPorData(data);
			
		}else {
			return dao.listar();
		}
	}
	
	
	// METODO CRIADO PARA RETORNAR UM PÁGINA FORMATADA
//	@RequestMapping("/relatorio-produtos")
//	@ResponseBody
//	public ModelAndView relatorio(String data) {
//		
//		
//		ModelAndView model = new ModelAndView("relatorio");
//		List<Produto> produtos = new ArrayList<>();
//		
//		
//		
//		if(data != null) {
//			produtos = dao.listarPorData(data);
//			
//			model.addObject("data", data);
//		}else {
//			produtos = dao.listar();
//		}
//		
//		model.addObject("produtos", produtos);
//		
//		return model;
//	}
}
