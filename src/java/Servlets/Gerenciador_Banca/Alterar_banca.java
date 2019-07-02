/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Banca;

import Dao.BancaDao;
import Dao.ProfessorDao;
import Dao.ProjetoDao;
import Dao.SituacaoDao;
import dominio.Banca;
import dominio.Professor;
import dominio.Projeto;
import dominio.Situacao;
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
public class Alterar_banca extends HttpServlet {

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
          
            
            
            BancaDao bancaDao = new BancaDao();
            Banca banca = new Banca();
            ProjetoDao projetoDao = new ProjetoDao();
            Projeto projeto = new Projeto();
            ProfessorDao avalidadorDao = new ProfessorDao();
            Professor avaliador1 = new Professor();
            Professor avaliador2 = new Professor();
            SituacaoDao situacaoDao = new SituacaoDao();
            Situacao situacao = new Situacao();
            
           int id_teste = 2;
            
           banca = bancaDao.getBanca(id_teste);
           
           int cod_projeto=0;
           int cod_avaliador1 = 0;
           int cod_avaliador2 = 2;
           int cod_situacao = 0;
            
            projeto = projetoDao.getProjeto(cod_projeto!=0?cod_projeto:banca.getProjeto().getId());
            avaliador1 = avalidadorDao.getProfessor(cod_avaliador1!=0?cod_avaliador1:banca.getAvaliador1().getId());
            avaliador2 = avalidadorDao.getProfessor(cod_avaliador2!=0?cod_avaliador2:banca.getAvaliador2().getId());
            situacao = situacaoDao.getSituacao(cod_situacao!=0?cod_situacao:banca.getSituacao().getId());
            
            banca.setProjeto(projeto);
            banca.setAvaliador1(avaliador1);
            banca.setAvaliador2(avaliador2);
            banca.setSituacao(situacao);
            
            
            bancaDao.altera(banca);
            out.println("<h2>Dados da banca alterados com sucesso!</h2>");
            out.println("<p>"+banca.getProjeto().getId()+"</p>");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_banca.class.getName()).log(Level.SEVERE, null, ex);
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
