package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	
	@RequestMapping("/usuarios")
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView("usuarios");
		model.addObject("usuarios", dao.listaDeUsuarios());
		return model;
	}
	
	@RequestMapping("/usuarios/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView model = new ModelAndView("usuarios-form");
		model.addObject("usuario", usuario);
		return model;
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult bind,
			RedirectAttributes redirectAtt) {
		
		
		if(bind.hasErrors()) {
			return form(usuario);
		}
		
		if(dao.existe(usuario)) {
			redirectAtt.addFlashAttribute("message", "O e-mail do usuário já existe em nosso sistema!");
			return form(usuario);
		}
		
		dao.gravar(usuario);
		redirectAtt.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		
		
		return new ModelAndView("redirect:/usuarios");
	}
}
