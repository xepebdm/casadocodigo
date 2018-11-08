package br.com.casadocodigo.loja.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
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
		
		// ADIÇÃO DE UMA ROLE USER COMO DEFAULT PARA EVITAR NULLPOINTER EM PARTES DO PROJETO
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("ROLE_USER"));
		usuario.setRoles(roles);
		
		manager.persist(usuario);
	}
	
	public List<Usuario> listaDeUsuarios(){
		return manager.createQuery("select u from Usuario u ", Usuario.class).getResultList();
	}

	public boolean existe(Usuario usuario) {
			List<Usuario> existe = manager.createQuery("select u from Usuario u where u.email = :pEmail", Usuario.class)
			.setParameter("pEmail", usuario.getEmail())
			.getResultList();
			
			if(existe.size() != 0) {
				return true;
			}
		return false;
	}

	public Usuario find(Long id) {
		return manager.createQuery("select u from Usuario u where u.id = :id", Usuario.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public void atualizar(Usuario usuario) {
		manager.merge(usuario);
	}

	public void buscaEditaAtualizaRoleDeUsuario(Long id, String[] role) {

		Usuario usuario = find(id);
		List<Role> roles = new ArrayList<Role>();
		
		for(int i = 0; i < role.length; i++) {
			
			if(!role[i].isEmpty()) {
				roles.add(new Role(role[i]));
			}
		}
		
		usuario.setRoles(roles);
		atualizar(usuario);
	}
}