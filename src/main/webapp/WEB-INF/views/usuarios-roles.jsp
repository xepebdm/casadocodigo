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

.button {
    background-color: #008CBA; /* Blue */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
}
</style>
</head>
	<div class="container">
		<h1>Cadastro de Permissões para <p>${usuarioNome }</p></h1>
		<h2>${erro }</h2>
		<h3>Ao menos uma permissão deve ser selecionada!</h3>
		<form:form action="${s:mvcUrl('UC#gravarRoles').arg(0, usuarioId).build() }" method="POST"
		commandName="roles">
			<p><label>Permissões:</label> 
			<form:checkboxes items="${roles }" path="${nome }" 
			value="${nome }" name="role"/>
			</p>
			<button type="submit" class="button">Cadastrar</button>
		</form:form>
	</div>

</tags:pageTemplate>