<%-- 
    Document   : listProjetosTitulo
    Created on : 30/08/2018, 01:51:30
    Author     : jpedro
--%>

<%@page import="dominio.TipoProjeto"%>
<%@page import="Dao.TipoProjetoDao"%>
<%@page import="Services.Service_projeto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.ProjetoDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="dominio.Projeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <%@include file="/head.jsp" %>
        <title>JSP Page</title>
        
    </head>
    <body>
        
        <%@include file="/navbar.jsp" %>
            <h1>Lista Projetos por Tipo de projeto</h1>

            <%     
    //                  

                        List<Projeto> projetos = new ArrayList<>();

                        Service_projeto Sproj = new Service_projeto();

                        String nome_titulo = request.getParameter("nm_titulo");

                        projetos = Sproj.ListaProjetoTitulo(nome_titulo);





            %>

            <div>
                <p>
                <form action="/ProjetoBanca/Projeto/ListaProjeto/listProjetosTitulo.jsp">
                    <label for="nm_titulo">Tipo de projeto</label>
                    <input type="text" name="nm_titulo"/>
                    <input type="submit" value="Buscar"/>

                </form>
                </p>


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
