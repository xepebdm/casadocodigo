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

		<c:if test="${not empty data }">
			<h1 class="cdc-call">Relatório de livros lançados apartir da
				data ${data }</h1>
		</c:if>
		<c:if test="${empty data }">
			<h1 class="cdc-call">Relatório de todos os livros</h1>
		</c:if>
		
		${dataGeracao }
		${quantidade }

		<table class="table table-striped" width="100%">
			<thead class="thead-light">
				<tr>
					<th scope="col">Título</th>
					<th scope="col">Descrição</th>
					<th scope="col">Data Lançamento</th>
				</tr>
			</thead>
			<c:forEach items="${produtos }" var="produto">
				<tr>
					<td>${produto.titulo }</td>
					<td>${produto.descricao }</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time }" /></td>
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>