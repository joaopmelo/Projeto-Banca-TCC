<%-- 
    Document   : listProjetoAreaPesquisa
    Created on : 06/08/2018, 09:41:40
    Author     : jpedro
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Dao.ProjetoDao"%>
<%@page import="java.util.List"%>
<%@page import="dominio.Projeto"%>
<%@page import="dominio.Projeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <title>Lista Projetos por Area de Pesquisa</title>
        <%@include file="/head.jsp" %>
        <title>JSP Page</title>
        
    </head>
    <body>
        
        <%@include file="/navbar.jsp" %>
        <h1>Lista Projetos por Area de Pesquisa</h1>
        
         <%     
//                  String nome = request.getParameter("joao");
                    
                    List<Projeto> projetos = new ArrayList<>();

                    ProjetoDao projDao = new ProjetoDao();
                    
                   
                    projetos = projDao.listaProjetoAreaPesquisa("Tecnologia");

                    
                
                    

                %>
                
        <div>
            <p><label>Digite o nome do aluno: </label>
<!--                <input type="text" name="nome"/><br><a href="listProjetosAluno.jsp?nome="><input type="button" value="Buscar"/></a></p>-->
            
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
                            <td><%out.print(p.getId());%></td>
				<td><%out.print(p.getTitulo());%></td>
                                <td><%out.print(p.getAluno().getNome());%></td> 
				<td><%out.print(p.getCurso().getNome());%></td>
				<td><%out.print(p.getTipoProjeto().getDescricao());%></td>
				<td><%out.print(p.getAreaPesquisa().getNome());%></td>
				<td><%out.print(p.getOrientador().getNome());%></td>
				
				<td></td>
			</tr>
                        <%}%>
		</table>
	</div>
      <%@include file="/footer.jsp" %>  
    </body>
</html>
