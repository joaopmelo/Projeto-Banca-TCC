/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_Avaliacao;

import Dao.AvaliacaoDao;
import Dao.BancaDao;
import dominio.Avaliacao;
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
public class Alterar_avaliacao extends HttpServlet {

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
           
            
            
            AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
            Avaliacao avaliacao = new Avaliacao();
            BancaDao bancaDao = new BancaDao();
            
            int id_teste=1;
            
            int cod_banca = 1;
            double nota = 6;
            String dataRealizacao = "06/08/2018";
            String resultado = "Aprovado";
            
            avaliacao = avaliacaoDao.getAvaliacao(id_teste);
            
            avaliacao.setBanca((cod_banca!=0)?bancaDao.getBanca(cod_banca):avaliacao.getBanca());
            avaliacao.setNota((nota!=0)?nota:avaliacao.getNota());
            avaliacao.setDataRealizacao((dataRealizacao!=null)?dataRealizacao:avaliacao.getDataRealizacao());
            avaliacao.setResultado((resultado!=null)?resultado:avaliacao.getResultado());
            
            
             try {
                avaliacaoDao.altera(avaliacao);
                out.println("<h2>Dados da avaliacao "+avaliacao.getId()+" alterados com sucesso!</h2>");

            } catch (Exception e) {
                out.println("<h2>Erro ao alterar dados da avaliacao!</h2>");
            }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_avaliacao.class.getName()).log(Level.SEVERE, null, ex);
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
