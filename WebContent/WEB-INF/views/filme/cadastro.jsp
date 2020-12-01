<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<meta charset="utf-8">
		<title>Formulario de Filme</title>
	</head>
	
	<body>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
				<a href="http://localhost:8080/loc" class="navbar-brand"> Locadora Digital</a>
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/generos" class="nav-link">Generos</a></li>
					<li><a href="<%=request.getContextPath()%>/categorias" class="nav-link">Categorias</a></li>
					<li><a href="<%=request.getContextPath()%>/filmes" class="nav-link">Filmes</a></li>
					<li><a href="<%=request.getContextPath()%>/clientes" class="nav-link">Clientes</a></li>
					<li><a href="<%=request.getContextPath()%>/locacaos" class="nav-link">Locações</a></li>
				</ul>				
			</nav>
		</header>
		
		<br>
		
		<div class="container col-md-8">
			<div class="card">
				<div class="card-body">
					
					<c:if test="${filme != null}">
						<caption>
							<h2>Editar Filme</h2>
						</caption>
					
						<form action="updateFilme" method="post" enctype="multipart/form-data">
							<input type="hidden" name="action" value="update" />
							<input type="hidden" name="id" value="<c:out value='${filme.id}' />" />
					
						<div class="container text-right">
							<img id="mostrarImagem" alt="Foto de Capa"  style="width: 100px; height: 130px"  /> 					
							<br>
							<input name="imagem" id="imagem" type="file" accept="image/*"  onchange="document.getElementById('mostrarImagem').src = window.URL.createObjectURL(this.files[0])">
						</div>	
					</c:if>
					
					
					<c:if test="${filme == null}">
						<caption>
							<h2>Novo Filme</h2>
						</caption>
					
						<form action="insertFilme" method="post" enctype="multipart/form-data">
							<input type="hidden" name="action" value="insert" />
					
						<div class="container text-right">
							<img id="mostrarImagem" alt="Foto de Capa"  style="width: 100px; height: 130px"  /> 					
							<br>
							<input name="imagem" id="imagem" type="file" accept="image/*"  onchange="document.getElementById('mostrarImagem').src = window.URL.createObjectURL(this.files[0])">
						</div>						
					</c:if>
	
						<fieldset class="form-group">
							<label>Título*</label> 
							<input name="titulo" type="text" value="<c:out value='${filme.titulo}' />" class="form-control" required="required">
						</fieldset>
						<form:errors path="filme.titulo" cssStyle="color:red" />
						
						<fieldset class="form-group">
							<label>Genero*</label> 
								<select name="genero" class="form-control" required="required">
										<option value="" >  --  Selecione --  </option>
									<c:forEach var="gen" items="${generos}">
										<option value="<c:out value='${gen}'/>"  <c:if test="${filme.genero.id == gen.id}">selected</c:if>  > <c:out value="${gen.nome}" /> </option>
									</c:forEach>
								</select>
						</fieldset>
						
						<fieldset class="form-group">
							<label>Cópias*</label> 
							<input name="copias" type="number" step="1" value="<c:out value='${filme.copias}' />" class="form-control" required="required">
						</fieldset>
						
						<fieldset class="form-group">
							<label>Categoria*</label> 
								<select name="categoria" class="form-control" required="required">
										<option value="" selected disabled hidden="hidden">  --  Selecione --  </option>
									<c:forEach var="categ" items="${categorias}">
										<option value="<c:out value='${categ}' />" <c:if test="${filme.categoria.id == categ.id}">selected</c:if> > <c:out value="${categ.nome}" /></option>
									</c:forEach>
									
								</select>
						</fieldset>
						
						<fieldset class="form-group">
							<label>Lançamento</label> 
							<input type="date" name="lancamento" step="1" value="<c:out value='${filme.lancamento}' />" class="form-control">
						</fieldset>
						
						<fieldset class="form-group">
							<label>Duração</label> 
							<input type="text" name="duracao" value="<c:out value='${filme.duracao}'/>" class="form-control" >
						</fieldset>
						
						<fieldset class="form-group">
							<label>Sinopse</label> 
							<textarea name="sinopse" rows="" cols="" class="form-control"><c:out value='${filme.sinopse}' /></textarea>
						</fieldset>
						
						<br>
						<br>
						
						<div class="container text-right">
							<a href="filme" class="btn btn-danger">Cancel</a>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-success">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>