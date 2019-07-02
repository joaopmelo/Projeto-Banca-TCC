/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import dominio.Projeto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface IProjetoDao {
    
    public void adicionar(Projeto projeto) throws SQLException;
    
    public void altera(Projeto projeto) throws SQLException;
    
    public void remove(Projeto projeto) throws SQLException;
    
    public Projeto getProjeto(Integer id) throws SQLException;
    
    public List<Projeto> listaProjeto() throws SQLException;
    
}
