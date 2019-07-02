<%-- 
    Document   : listanca
    Created on : 06/08/2018, 10:14:05
    Author     : jpedro
--%>

<%@page import="Dao.BancaDao"%>
<%@page import="dominio.Banca"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Lista Projetos por Banca</h1>
        
         <%     
//                  String nome = request.getParameter("joao");
                    
                    List<Banca> bancas = new ArrayList<>();

                    BancaDao bancaDao = new BancaDao();
                    
                   
                    bancas = bancaDao.listaABanca();

                    
                    
                    

                %>
                
        <div>
            <p><label>Digite o nome do aluno: </label>
<!--                <input type="text" name="nome"/><br><a href="listProjetosAluno.jsp?nome="><input type="button" value="Buscar"/></a></p>-->
            
		<table>
			<tr>
				<th>ID</th>
				<th>TITULO</th>
				<th>AVALIADOR 1</th>
				<th>AVALIADOR 1</th>
				<th>SITUAÇÃO</th>
				
			</tr>
                        <%  for (Banca b : bancas) {
                        %>
			<tr>
                                <td><%out.print(b.getId());%></td>
				<td><%out.print(b.getProjeto().getTitulo());%></td>
                                <td><%out.print(b.getAvaliador1().getNome());%></td> 
				<td><%out.print(b.getAvaliador2().getNome());%></td>
				<td><%out.print(b.getSituacao().getDescricao());%></td>
				
				
				<td></td>
			</tr>
                        <%}%>
		</table>
	</div>
    </body>
</html>
