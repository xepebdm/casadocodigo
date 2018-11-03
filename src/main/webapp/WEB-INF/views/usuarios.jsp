<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="index-section" class="container middle">
		<a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a>
		<p>${sucesso}</p>
		<p>${falha}</p>
		<h2>Lista de Usuários</h2>
		<table class="table table-striped" width="100%">
			<thead class="thead-light">
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
				</tr>
			</thead>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>