/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
 * @author CADASTRO02
 */
public class Gerencia_aluno extends HttpServlet {

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
            
            int opcao=1;
            
            // OPCAO INCLUIR
            switch (opcao) {
                case 1:
                    {
                        //            String nomeAluno = request.getParameter("nome-aluno");
//            String cpfAluno = request.getParameter("cpf-aluno");
//            String rgAluno = request.getParameter("rg-aluno");
//            String emailAluno = request.getParameter("email-aluno");
//            int matricula = Integer.parseInt(request.getParameter("matricula-aluno"));
                        
                        String nomeAluno = "Paulo Ricardo6";
                        String cpfAluno = "2222222";
                        String rgAluno = "10102121";
                        String emailAluno = "parpei@gmail.com";
                        int matricula = 154786;
                        aluno.setNome(nomeAluno);
                        aluno.setCpf(cpfAluno);
                        aluno.setRg(rgAluno);
                        aluno.setEmail(emailAluno);
                        aluno.setMatricula(matricula);
                        try {
                            alunoDao.adiciona(aluno);
                            out.println("<h2>Aluno cadastrado com sucesso!</h2>");
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro ao cadastrar aluno!</h2>");
                        }       break;
                    }
                case 2:
                    {
                        int id_teste=5;
                        
                        aluno = alunoDao.getAluno(id_teste);
                        
                        int id = aluno.getId();
                        String nome = aluno.getNome();
                        String cpf = aluno.getCpf();
                        String rg = aluno.getRg();
                        String email = aluno.getEmail();
                        int matricula = aluno.getMatricula();
                        
                        
                        
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
                        }       break;
                    }
                case 3:
                    {
                        int id_teste = 10;
                                                
                        aluno.setId(id_teste);
                        
                        try {
                            alunoDao.remove(aluno);
                            out.println("<h2>Aluno removido com sucesso!</h2>");
                            
                        } catch (Exception e) {
                            out.println("<h2>Erro ao remover aluno!</h2>");
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
