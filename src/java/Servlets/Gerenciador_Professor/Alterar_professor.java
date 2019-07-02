/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Professor;

import Dao.ProfessorDao;
import dominio.Professor;
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
public class Alterar_professor extends HttpServlet {

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
            
            ProfessorDao professorDao = new ProfessorDao();
            Professor professor = new Professor();
            
            int id_teste=2;
            
            String nomeProf = "Bruno Costa";
            String cpfProf = "555555";
            String rgProf = "505050";
            String emailProf = "brunoCosta@gmail.com";
            int registroProf = 10258;
            double salarioProf = 12600.50;
            
            professor = professorDao.getProfessor(id_teste);

            int id = professor.getId();
            String nome = (nomeProf!=null)?nomeProf:professor.getNome();
            String cpf = (cpfProf!=null)?cpfProf:professor.getCpf();
            String rg = (rgProf!=null)?rgProf:professor.getRg();
            String email = (emailProf!=null)?emailProf:professor.getEmail();
            int registro = (registroProf!=0)?registroProf:professor.getRegistro();
            double salario = (salarioProf!=0)?salarioProf:professor.getSalario();



            professor.setNome(nome);
            professor.setCpf(cpf);
            professor.setRg(rg);
            professor.setEmail(email);
            professor.setRegistro(registro);
            professor.setSalario(salario);


            try {
                professorDao.altera(professor);
                out.println("<h2>Dados do Aluno "+professor.getNome()+" alterados com sucesso!</h2>");

            } catch (Exception e) {
                out.println("<h2>Erro ao alterar dados do aluno!</h2>");
            }  
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_professor.class.getName()).log(Level.SEVERE, null, ex);
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
