/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Projeto;

import Dao.AlunoDao;
import Dao.AreaDePesquisaDao;
import Dao.CursoDao;
import Dao.ProfessorDao;
import Dao.ProjetoDao;
import Dao.TipoProjetoDao;
import Services.Service_projeto;
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
 * @author jpedro
 */
public class Alterar_projeto extends HttpServlet {

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
            
            Service_projeto Sproj = new Service_projeto();
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
            
            int idProj= Integer.parseInt(request.getParameter("id_proj"));
                        
                        projeto = projetoDao.getProjeto(idProj);
                        
                        // Recebe os dados do projeto
                        String titulo = request.getParameter("titulo");
                        int cod_aluno = Integer.parseInt(request.getParameter("opt_aluno"));
                        int cod_curso = Integer.parseInt(request.getParameter("opt_curso"));
                        int cod_tipoProjeto = Integer.parseInt(request.getParameter("opt_tproj"));
                        int cod_pesquisa = Integer.parseInt(request.getParameter("opt_area"));
                        int cod_orientador = Integer.parseInt(request.getParameter("opt_orientador"));
                        
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
                            projeto.setTitulo(titulo);
                            projeto.setAluno(aluno);
                            projeto.setCurso(curso);
                            projeto.setTipoProjeto(tipoProjeto);
                            projeto.setAreaPesquisa(areaPesquisa);
                            projeto.setOrientador(orientador);
                            
     
                        
                       
                        try {
                            Sproj.alteraProjeto(projeto);
                            response.sendRedirect("/ProjetoBanca/Projeto/ListaProjeto/listarProjetosTodos.jsp");
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro ao alterar os dados do projeto </h2>");
                        }   
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_projeto.class.getName()).log(Level.SEVERE, null, ex);
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
