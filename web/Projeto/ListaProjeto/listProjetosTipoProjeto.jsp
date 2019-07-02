<%-- 
    Document   : listProjetosTipoProjeto
    Created on : 06/08/2018, 09:32:12
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

                        int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));

                        projetos = Sproj.ListaProjetoTipo(id_tipo);





            %>

            <div>
                <p>
                <form action="/ProjetoBanca/Projeto/ListaProjeto/listProjetosTipoProjeto.jsp">
                    <label for="tipo_projeto">Tipo de projeto</label>
                            <div class="div_texbox">
                                <%                                
                                     TipoProjetoDao tprojDao = new TipoProjetoDao();
                                    List<TipoProjeto> tprojs = tprojDao.listaTipoProjeto();




                                    out.print("<select name=\"id_tipo\"> ");
                                    out.print("<option selected>Selecione o tipo de projeto</option>");

                                    for (TipoProjeto t : tprojs) {

                                            out.print("<option value="
                                                    + t.getId() + ">"
                                                    + t.getDescricao()+ "</option>");

                                    }
                                    out.print("</select>");



                                %>
                                <input type="submit" value="Buscar"/>
                            </div>

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
