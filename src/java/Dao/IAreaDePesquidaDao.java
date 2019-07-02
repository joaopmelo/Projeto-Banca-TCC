/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import dominio.AreaPesquisa;
import dominio.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface IAreaDePesquidaDao {
    
    public void adicionar(AreaPesquisa areaPesquisa) throws SQLException;
    
    public void altera(AreaPesquisa areaPesquisa) throws SQLException;
    
    public void remove(AreaPesquisa areaPesquisa) throws SQLException;
    
    public AreaPesquisa getAreaPesquisa(Integer id) throws SQLException;
    
    public List<AreaPesquisa> listaAreaPesquisa() throws SQLException;
}
