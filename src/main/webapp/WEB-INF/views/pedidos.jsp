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
	<h2>Lista de Pedidos atuais</h2>
		<table class="table table-striped" width="100%">
			<thead class="thead-light">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Valor</th>
					<th scope="col">Data do pedido</th>
					<th scope="col">Títulos</th>
				</tr>
			</thead>
			<c:forEach items="${pedidos }" var="pedido" varStatus="indice">
				<tr>
					<td>${pedido.id }</td>
					<td>${pedido.valor }</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.data.time }" /></td>
					<td><c:forEach varStatus="i" begin="0" end="${pedido.produtos.size() -1 }">
									${pedido.produtos.get(i.index).titulo },					
					</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</section>

</tags:pageTemplate>