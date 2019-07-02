/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Dao.AlunoDao;
import Dao.AreaDePesquisaDao;
import Dao.CursoDao;
import Dao.ProfessorDao;
import Dao.ProjetoDao;
import Dao.TipoProjetoDao;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.TipoProjeto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CADASTRO02
 */
public class Gerencia_projeto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ProjetoDao projetoDao = new ProjetoDao();
            Projeto projeto = new Projeto();
            AlunoDao alunoDao = new AlunoDao();
            Aluno aluno = new Aluno();
            CursoDao cursoDao = new CursoDao();
            Curso curso = new Curso();
            TipoProjetoDao tipoProjetoDao = new TipoProjetoDao();
            TipoProjeto tipoProjeto = new TipoProjeto();
            AreaDePesquisaDao areaPesquisaDao = new AreaDePesquisaDao();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
            ProfessorDao orientadorDao = new ProfessorDao();
            Professor orientador = new Professor();
            
            int opcao=3;
            
            // OPCAO INCLUIR
            switch (opcao) {
                case 1:
                    {
//            String nomeAluno = request.getParameter("nome-aluno");
//            String cpfAluno = request.getParameter("cpf-aluno");
//            String rgAluno = request.getParameter("rg-aluno");
//            String emailAluno = request.getParameter("email-aluno");
//            int matricula = Integer.parseInt(request.getParameter("matricula-aluno"));
                        
                        String titulo = "Como os appfsfsdf sadsad a saúde pública";
                        int cod_aluno = 1;
                        int cod_curso = 1;
                        int cod_tipoProjeto = 1;
                        int cod_pesquisa = 1;
                        int cod_orientador = 1;
                        
                        aluno = alunoDao.getAluno(cod_aluno);
                        curso = cursoDao.getCurso(cod_curso);
                        tipoProjeto = tipoProjetoDao.getTipoProjeto(cod_tipoProjeto);
                        areaPesquisa = areaPesquisaDao.getAreaPesquisa(cod_pesquisa);
                        orientador = orientadorDao.getProfessor(cod_orientador);
                        
                        projeto.setTitulo(titulo);
                        projeto.setAluno(aluno);
                        projeto.setCurso(curso);
                        projeto.setTipoProjeto(tipoProjeto);
                        projeto.setAreaPesquisa(areaPesquisa);
                        projeto.setOrientador(orientador);
                        
                        
                        
                        try {
                            projetoDao.adicionar(projeto);
                            out.println("<h2>Projeto "+projeto.getTitulo()+" cadastrado com sucesso!</h2>");
                            out.println("<p>"+projeto.getAluno().getId()+"</p>");
                            out.println("<p>"+projeto.getCurso().getId()+"</p>");
                            out.println("<p>"+projeto.getTipoProjeto().getId()+"</p>");
                            out.println("<p>"+projeto.getAreaPesquisa().getId()+"</p>");
                            out.println("<p>"+projeto.getOrientador().getId()+"</p>");
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro "+projeto.getTitulo()+" ao projeto aluno!</h2>");
                        }       break;
                    }
                case 2:
                    {
                        int id_teste=10;
                        
                        projeto = projetoDao.getProjeto(id_teste);
                        
                        // Recebe os dados do projeto
                        String titulo = projeto.getTitulo();
                        int cod_aluno = projeto.getAluno().getId();
                        int cod_curso = projeto.getCurso().getId();
                        int cod_tipoProjeto = projeto.getTipoProjeto().getId();
                        int cod_pesquisa = projeto.getAreaPesquisa().getId();
                        int cod_orientador = projeto.getOrientador().getId();
                        
                        try {
                            // Carrega os objetos do projeto
                            aluno = alunoDao.getAluno(cod_aluno);
                            curso = cursoDao.getCurso(cod_curso);
                            tipoProjeto = tipoProjetoDao.getTipoProjeto(cod_tipoProjeto);
                            areaPesquisa = areaPesquisaDao.getAreaPesquisa(cod_pesquisa);
                            orientador = orientadorDao.getProfessor(cod_orientador);
                        } catch (Exception e) {
                            out.println("<h2>ERRO ao pegar os objetos!</h2>");
                        }
                        
                        
                     
                            //Insere os dados no projeto novamente
                            projeto.setTitulo("234324324 23423423 3242");
                            projeto.setAluno(aluno);
                            projeto.setCurso(curso);
                            projeto.setTipoProjeto(tipoProjeto);
                            projeto.setAreaPesquisa(areaPesquisa);
                            projeto.setOrientador(orientador);
                            
     
                        
                       
                        try {
                            projetoDao.altera(projeto);
                            out.println("<h2>Dados do projeto "+projeto.getTitulo()+" alterados com sucesso!</h2>");
                            out.println("<p>"+projeto.getAluno().getId()+"</p>");
                            out.println("<p>"+projeto.getCurso().getId()+"</p>");
                            out.println("<p>"+projeto.getTipoProjeto().getId()+"</p>");
                            out.println("<p>"+projeto.getAreaPesquisa().getId()+"</p>");
                            out.println("<p>"+projeto.getOrientador().getId()+"</br></br></br></br></p>");
                            
                            
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro ao alterar os dados do projeto "+projeto.getTitulo()+"!</h2>");
                        }       break;
                    }
                case 3:
                    {
                        int id_teste = 10;
                                                
                        projeto.setId(id_teste);
                        
                        try {
                            projetoDao.remove(projeto);
                            out.println("<h2>Projeto removido com sucesso!</h2>");
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro ao remover Projeto!</h2>");
                        }       break;
                    }
                default:
                    break;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Gerencia_aluno.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
