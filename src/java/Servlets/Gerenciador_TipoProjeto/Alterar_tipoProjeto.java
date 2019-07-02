/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Gerenciador_TipoProjeto;

import Dao.TipoProjetoDao;
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
public class Alterar_tipoProjeto extends HttpServlet {

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
           
            
            TipoProjetoDao tipoProjetoDao = new TipoProjetoDao();
            TipoProjeto tipoProjeto = new TipoProjeto();
            
            int id_teste=3;
            
            tipoProjeto = tipoProjetoDao.getTipoProjeto(id_teste);
            
            String descricao = "Nano bots"; // Seta o valor
            
            tipoProjeto.setDescricao((descricao!=null)?descricao:tipoProjeto.getDescricao());
            
            try {
                tipoProjetoDao.altera(tipoProjeto);
                out.println("<h2>Descrição do tipo de projeto "+tipoProjeto.getDescricao()+" alterados com sucesso!</h2>");

            } catch (Exception e) {
                out.println("<h2>Erro ao alterar a descrição do tipo de projeto!</h2>");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Alterar_tipoProjeto.class.getName()).log(Level.SEVERE, null, ex);
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
