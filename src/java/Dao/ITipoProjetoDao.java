/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import dominio.TipoProjeto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface ITipoProjetoDao {
    
    public void adicionar(TipoProjeto tProjeto) throws SQLException;
    
    public void altera(TipoProjeto tProjeto) throws SQLException;
    
    public void remove(TipoProjeto tProjeto) throws SQLException;
    
    public TipoProjeto getTipoProjeto(Integer id) throws SQLException;
    
    public List<TipoProjeto> listaTipoProjeto() throws SQLException;
}
