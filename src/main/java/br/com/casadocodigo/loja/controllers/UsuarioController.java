package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation(dao));
	}
	

	
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView model = new ModelAndView("usuarios-form");
		model.addObject("usuario", usuario);
		return model;
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult bind, RedirectAttributes redirectAtt) {
		ModelAndView model = new ModelAndView("redirect:/usuarios");
		
		if(bind.hasErrors()) {
			return form(usuario);
		}
		dao.gravar(usuario);
		redirectAtt.addFlashAttribute("sucesso", "Usuário "+ usuario.getNome() + " cadastrado com sucesso!");
		
		
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView("usuarios");
		model.addObject("usuarios", dao.listaDeUsuarios());
		return model;
	}
}
