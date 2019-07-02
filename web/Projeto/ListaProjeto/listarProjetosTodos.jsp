<%-- 
    Document   : listaProjetos
    Created on : 06/08/2018, 08:51:41
    Author     : jpedro
--%>

<%@page import="Dao.ProjetoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Projeto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>JSP Page</title>
        
        <%@include file="/head.jsp" %>
        <title>JSP Page</title>
        
    </head>
    <body>
        
        <%@include file="/navbar.jsp" %>
        
        <h1>Lista Projetos</h1>
        
         <%
                    List<Projeto> projetos = new ArrayList<>();

                    ProjetoDao projDao = new ProjetoDao();

                    projetos = projDao.listaProjeto();


                %>
                
        <div>
		<table>
			<tr>
				<th>ID</th>
				<th>TITULO</th>
				<th>ALUNO</th>
				<th>CURSO</th>
				<th>TIPO DE PROJETO</th>
				<th>AREA DE PESQUISA</th>
				<th>ORIENTADOR</th>
				<th>OP</th>
			</tr>
                        <%  for (Projeto p : projetos) {
                        %>
			<tr>
                            <td class="id"><%out.print(p.getId());%></td>
                            <td class="titulo"><%out.print(p.getTitulo());%></td>
                            <td class="orientador"><%out.print(p.getAluno().getNome());%></td>
                            <td class="aluno"><%out.print(p.getCurso().getNome());%></td>
                            <td class="curso"><%out.print(p.getTipoProjeto().getDescricao());%></td>
                            <td class="tipo_p"><%out.print(p.getAreaPesquisa().getNome());%></td>
                            <td class="area_p"><%out.print(p.getOrientador().getNome());%></td>
                            
                            <td class="operacao">
                                <a href="/ProjetoBanca/editaProjeto.jsp?idProj=<%out.print(p.getId());%>">EDITAR</a>
                                <a href="/ProjetoBanca/Excluir_projeto?idProj=<%out.print(p.getId());%>">EXCLUIR</a>
                            </td>
			</tr>
                        <%}%>
		</table>
	</div>
       <%@include file="/footer.jsp" %>  
    </body>
</html>
