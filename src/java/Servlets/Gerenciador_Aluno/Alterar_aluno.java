/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Aluno;

import Dao.AlunoDao;
import dominio.Aluno;
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
public class Alterar_aluno extends HttpServlet {

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
            
            int id_teste=3;
                        
            String nome_aluno = null;
            String cpf_aluno = null;
            String rg_aluno = null;
            String email_aluno = null;
            int matricula_aluno = 0;
                    
            aluno = alunoDao.getAluno(id_teste);

            int id = aluno.getId();
            String nome = (nome_aluno!=null)?nome_aluno:aluno.getNome();
            String cpf = (cpf_aluno!=null)?cpf_aluno:aluno.getCpf();
            String rg = (rg_aluno!=null)?rg_aluno:aluno.getRg();
            String email = (email_aluno!=null)?email_aluno:aluno.getEmail();
            int matricula = (matricula_aluno!=0)?matricula_aluno:aluno.getMatricula();



            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setRg(rg);
            aluno.setEmail(email);
            aluno.setMatricula(matricula);


            try {
                alunoDao.altera(aluno);
                out.println("<h2>Dados do Aluno "+aluno.getNome()+" alterados com sucesso!</h2>");

            } catch (Exception e) {
                out.println("<h2>Erro ao alterar dados do aluno!</h2>");
            }   
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_aluno.class.getName()).log(Level.SEVERE, null, ex);
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
