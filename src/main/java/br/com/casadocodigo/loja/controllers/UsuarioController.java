package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@Autowired
	private RoleDAO roleDao;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView("usuarios");
		model.addObject("usuarios", dao.listaDeUsuarios());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult bind, RedirectAttributes redirectAtt) {
		ModelAndView model = new ModelAndView("redirect:/usuarios");

		if (bind.hasErrors()) {
			return form(usuario);
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
		
		usuario.setSenha(encodedPassword);
		usuario.setConfSenha(encodedPassword);
		
		//ADICIONADA ROLE USER POR PADRÃO NO PERFIL DO USUÁRIO CRIADO PARA EVITAR NULLPOINTER EM PARTES DO PROJETO
		Role roleUser = new Role("ROLE_USER");
		usuario.setRoles(Arrays.asList(roleUser));
		
		dao.gravar(usuario);
		redirectAtt.addFlashAttribute("sucesso", "Usuário " + usuario.getNome() + " cadastrado com sucesso!");

		return model;
	}

	@RequestMapping("/roles")
	public ModelAndView roles(Long id) {
		ModelAndView model = new ModelAndView("usuarios-roles");
		Usuario usuario = dao.find(id);
		
		String[] roles = roleDao.arrayDeRoles();
		
		
		model.addObject("usuarioId", id);
		model.addObject("usuarioNome", usuario.getNome());
		model.addObject("roles", roles);

		return model;
	}

	@RequestMapping("/roles-add")
	public ModelAndView gravarRoles(Long idUsuario, @RequestParam String[] role, RedirectAttributes redirectAtt) {
		ModelAndView model = new ModelAndView("redirect:/usuarios");
		
		// TRATAMENTO CASO NÃO SEJA SELECIONADO NENHUMA ROLE PARA O PERFIL DO USUARIO
		// USUARIOS SEM ROLES OCASIONA EM NULLPOINTER EM PARTES DO PROJETO
		if(role.length <= 1) {
			return roles(idUsuario);
		}

		// ATUALIZA O USUARIO APARTIR DO ID E ADICIONA AS ROLES AO USUARIO
		dao.buscaEditaAtualizaRoleDeUsuario(idUsuario, role);


		redirectAtt.addFlashAttribute("sucesso", "Permissões editadas com sucesso!");


		return model;
	}

}
