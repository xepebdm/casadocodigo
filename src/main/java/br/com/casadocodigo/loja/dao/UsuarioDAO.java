package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public List<Usuario> listaDeUsuarios(){
		return manager.createQuery("select u from Usuario u ", Usuario.class).getResultList();
	}

	public boolean existe(Usuario usuario) {
			int existe = manager.createQuery("select count(u.email) from Usuario u where u.email = :pEmail", Usuario.class)
			.setParameter("pEmail", usuario.getEmail())
			.getFirstResult();
			
			if(existe == 0) {
				return true;
			}
		return false;
	}
}