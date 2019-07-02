<%-- 
    Document   : IncluiProjeto
    Created on : 28/08/2018, 00:29:05
    Author     : jpedro
--%>

<%@page import="dominio.Professor"%>
<%@page import="Dao.ProfessorDao"%>
<%@page import="dominio.AreaPesquisa"%>
<%@page import="Dao.AreaDePesquisaDao"%>
<%@page import="dominio.TipoProjeto"%>
<%@page import="Dao.TipoProjetoDao"%>
<%@page import="dominio.Curso"%>
<%@page import="Dao.CursoDao"%>
<%@page import="java.util.List"%>
<%@page import="Dao.AlunoDao"%>
<%@page import="dominio.Aluno"%>
<%@page import="Dao.ProjetoDao"%>
<%@page import="dominio.Projeto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Projeto - Incluir</title>
        <%@include file="/head.jsp" %>

    </head>
    <body>
        
        <%@include file="/navbar.jsp" %>

            <div id="top">
                <center><h1>Cadastro de Projeto - Incluir</h1></center>
            </div> 

            <div id="leftSide">

                <form name = "frmEditaProjeto" method="POST" action="Incluir_projeto">

                    <fieldset>
                        
                        <legend>Dados do Projeto</legend>
                        <label for="aluno">Aluno:</label>
                        <div class="div_texbox">
                            <%                                
                                AlunoDao alunoDao = new AlunoDao();
                                List<Aluno> alunos = alunoDao.listaAlunos();

                                out.print("<tr align=left>");
                                
                                out.print("<td align=\"left\" colspan=\"3\">");
                                out.print("<select name=\"opt_aluno\"> ");
                                out.print("<option selected>Selecione o aluno</option>");

                                for (Aluno a : alunos) {

                                        out.print("<option value="
                                                + a.getId() + ">"
                                                + a.getNome() + "</option>");
            
                                }
                                out.print("</select>");
                               
                                out.print("</tr>");

                            %>
                        </div>

                        <label for="titulo"> Titulo do Projeto:</label>
                        <div class="div_texbox">
                            <input name="titulo" type="text" class="usuario"/>
                        </div>
                        
                        <label for="curso">curso</label>
                        <div class="div_texbox">
                            <%                                
                                CursoDao cursoDao = new CursoDao();
                                List<Curso> cursos = cursoDao.listaCurso();

                                out.print("<tr align=left>");
                               
                                out.print("<td align=\"left\" colspan=\"3\">");
                                out.print("<select name=\"opt_curso\">");
                                out.print("<option selected>Selecione o curso</option>");

                                for (Curso c : cursos) {

                                        out.print("<option value="
                                                + c.getId() + ">"
                                                + c.getNome() + "</option>");
            
                                }
                                out.print("</select>");
                             
                                out.print("</tr>");

                            %>
                        </div>
                        
                        
                        <label for="tipo_projeto">Tipo de projeto</label>
                        <div class="div_texbox">
                            <%                                
                                 TipoProjetoDao tprojDao = new TipoProjetoDao();
                                List<TipoProjeto> tprojs = tprojDao.listaTipoProjeto();

                                out.print("<tr align=left>");
                             
                                out.print("<td align=\"left\" colspan=\"3\">");
                                out.print("<select name=\"opt_tproj\"> ");
                                out.print("<option selected>Selecione o tipo de projeto</option>");

                                for (TipoProjeto t : tprojs) {

                                        out.print("<option value="
                                                + t.getId() + ">"
                                                + t.getDescricao()+ "</option>");
            
                                }
                                out.print("</select>");
                             
                                out.print("</tr>");

                            %>
                        </div>
                       
                        <label for="area_pesquisa">Area de pesquisa</label>
                        <div class="div_texbox">
                            <%                                
                                AreaDePesquisaDao areaDao = new AreaDePesquisaDao();
                                List<AreaPesquisa> areas = areaDao.listaAreaPesquisa();

                                out.print("<tr align=left>");
                              
                                out.print("<td align=\"left\" colspan=\"3\">");
                                out.print("<select name=\"opt_area\"> ");
                                out.print("<option selected>Selecione o tipo de projeto</option>");

                                for (AreaPesquisa area : areas) {

                                        out.print("<option value="
                                                + area.getId() + ">"
                                                + area.getNome()+ "</option>");
            
                                }
                                out.print("</select>");
                            
                                out.print("</tr>");

                            %>
                        </div>
                        
                        <label for="orientador">Orientador</label>
                        <div class="div_texbox">
                            <%                                
                                 ProfessorDao oriDao = new ProfessorDao();
                                List<Professor> oris = oriDao.listaProfessores();

                                out.print("<tr align=left>");
                              
                                out.print("<td align=\"left\" colspan=\"3\">");
                                out.print("<select name=\"opt_orientador\"> ");
                                out.print("<option selected>Selecione o orientador</option>");

                                for (Professor ori : oris) {

                                        out.print("<option value="
                                                + ori.getId() + ">"
                                                + ori.getNome()+ "</option>");
            
                                }
                                out.print("</select>");
                         
                                out.print("</tr>");

                            %>
                        </div>
                        
                    </fieldset>

                    

                    <div class="button_div">
                        <input type="submit" name="cad" value="Salvar">
                        <input type="reset" name="cancelar" value="Limpar">
                                
                    </div>

              
                </form>




            </div>

       <%@include file="/footer.jsp" %>  

    </body>
</html>
