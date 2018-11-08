<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	<head>
<style>
h3 {
	text-align: justify;
}
</style>
	</head>

	<section id="index-section" class="container middle">
		<a href="${s:mvcUrl('UC#form').build()}" method="GET">Novo Usuário</a>
		<h3>${sucesso}</h3>
		<h3>Lista de Usuários</h3>
		<table class="table table-striped" width="100%">
			<thead class="thead-light">
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
					<th scope="col">ROLES</th>
					<th scope="col">ADD/Edit</th>
				</tr>
			</thead>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td><c:forEach varStatus="i" begin="0"
							end="${usuario.roles.size() -1 }">
									- ${usuario.roles.get(i.index).nome }					
					</c:forEach></td>
					<td><a href="${s:mvcUrl('UC#roles').arg(0, usuario.id ).build()}" method="get">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>