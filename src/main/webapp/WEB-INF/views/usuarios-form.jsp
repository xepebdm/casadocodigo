<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<div class="container">
		<h1>Cadastro de Usuário</h1>
		<p>${sucesso}</p>
		<p>${falha}</p>
		<form:form action="${s:mvcUrl('UC#gravar').build() }" method="POST"
			commandName="usuario">
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" />
			</div>
			<div class="form-group">
				<label>E-mail</label>
				<form:textarea path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div class="form-group">
				<label>Senha</label>
				<form:input path="senha" cssClass="form-control" />
				<form:errors path="senha" />
			</div>
			<div class="form-group">
				<label>Confirmar Senha</label>
				<form:input path="confSenha" cssClass="form-control" />
				<form:errors path="confSenha" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>

</tags:pageTemplate>