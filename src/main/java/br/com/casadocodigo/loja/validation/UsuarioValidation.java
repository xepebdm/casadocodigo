package br.com.casadocodigo.loja.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;


public class UsuarioValidation implements Validator {
	
	
	private UsuarioDAO dao;
	
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	
	public UsuarioValidation(UsuarioDAO dao) {
		this.dao = dao;
	}
	public UsuarioValidation() {}

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getNome().length() < 3) {
			errors.rejectValue("nome", "field.required.nome.invalido");
		}
		
		if(usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "field.required.tamanho.invalido");
			
		}
		
		if(!usuario.getSenha().equals(usuario.getConfSenha())){
			errors.rejectValue("confSenha", "field.required.conf-senha");
		}
		
		Matcher matcher = pattern.matcher(usuario.getEmail());
		if(!matcher.matches()){
			errors.rejectValue("email", "field.required.email.invalido");
		}
		
		if(dao.existe(usuario)) {
			errors.rejectValue("email", "field.required.email.existe");
		}
		
		
	}

}
