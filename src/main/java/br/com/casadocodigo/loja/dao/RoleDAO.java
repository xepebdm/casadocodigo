package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Role role) {
		manager.persist(role);
	}

	public List<Role> listaDeRoles() {
		return manager.createQuery("select r from Role r", Role.class)
				.getResultList();
	}

	
	// CONVERSÃO DA LIST EM ARRAY PARA MANUSEAR NA JSP E ADICIONAR NO PERFIL DO USUARIO
	public String[] arrayDeRoles() {
		List<Role> listRoles = listaDeRoles();
		String[] roles = new String[listaDeRoles().size()];
		
		for(int i = 0; i < roles.length; i++) {
			roles[i] = listRoles.get(i).getNome();
		}
		return roles;
	}
}
