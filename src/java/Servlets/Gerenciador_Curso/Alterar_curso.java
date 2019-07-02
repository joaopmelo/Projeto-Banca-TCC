/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Curso;

import Dao.CursoDao;
import dominio.Curso;
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
public class Alterar_curso extends HttpServlet {

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
            
            CursoDao cursoDao = new CursoDao();
            Curso curso = new Curso();
            
            int id_teste=2;
            
            curso = cursoDao.getCurso(id_teste);
            
            String nome_curso = null;
            String instituicao = null;
            int cargaHoraria = 0;
            
            curso.setNome((nome_curso!=null)?nome_curso:curso.getNome());
            curso.setInstituicao((instituicao!=null)?instituicao:curso.getInstituicao());
            curso.setCargaHoraria((cargaHoraria!=0)?cargaHoraria:curso.getCargaHoraria());
            
            
            try {
                cursoDao.altera(curso);
                out.println("<h2>Dados do curso "+curso.getNome()+" alterados com sucesso!</h2>");

            } catch (Exception e) {
                out.println("<h2>Erro ao alterar dados do curso!</h2>");
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_curso.class.getName()).log(Level.SEVERE, null, ex);
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
