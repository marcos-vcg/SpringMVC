  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <html>
  <body>

    <a href="novoFilme">Novo Filme</a>

    <br /> <br />

    <table>
      <tr>
        <th>Id</th>
        <th>Titulo</th>
        <th>Genero</th>
        <th>Lancamento</th>
        <th>Ações</th>
      </tr>
      <c:forEach items="${filmes}" var="filme">
        <tr>
          <td>${filme.id}</td>
          <td>${filme.titulo}</td>
          <td>${filme.genero.nome}</td>
          <td>${filme.lancamento}</td>
          <td>
	           <a href="">Editar</a>
	           &nbsp;&nbsp;
	           <a  href="">Excluir</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
  </html>