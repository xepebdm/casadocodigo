package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;


public class UsuarioValidation implements Validator {
	
	
	private UsuarioDAO dao;

	
	public UsuarioValidation(UsuarioDAO dao) {
		this.dao = dao;
	}
	public UsuarioValidation() {
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		
		Usuario usuario = (Usuario) target;
		if(usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "field.required.tamanho.invalido");
			
		}
		
		if(!usuario.getSenha().equals(usuario.getConfSenha())){
			errors.rejectValue("confSenha", "field.required.conf-senha");
		}
		
		if(dao.existe(usuario)) {
			errors.rejectValue("email", "field.required.email.existe");
		}
		
		
	}

}
