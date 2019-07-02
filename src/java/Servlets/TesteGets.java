/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Dao.AlunoDao;
import Dao.AreaDePesquisaDao;
import Dao.BancaDao;
import Dao.CursoDao;
import Dao.ProfessorDao;
import Dao.ProjetoDao;
import Dao.TipoProjetoDao;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Banca;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.TipoProjeto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class TesteGets extends HttpServlet {

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
            
                   
            
//            List<Projeto> projetos = new ArrayList<>();
//            ProjetoDao projetoDao = new ProjetoDao();
//            
//          
//                projetos = projetoDao.listaProjetoTitulo("apps");
//          
//            
//            
//            for (Projeto p : projetos){
//                
//                
//                 out.println("<br>Titulo do projeto: "+p.getId());
//            
//            }
            
            List<Banca> bancas = new ArrayList<>();
            BancaDao bancaDao = new BancaDao();
            
          
                bancas = bancaDao.listaABanca();
          
            
            
            for (Banca b: bancas){
                
                
                 out.println("<br>Titulo do projeto: "+b.getId());
            
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(TesteGets.class.getName()).log(Level.SEVERE, null, ex);
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
