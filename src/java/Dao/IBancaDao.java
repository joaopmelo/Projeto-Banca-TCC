/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import dominio.Banca;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface IBancaDao {
    
    public void adicionar(Banca banca) throws SQLException;
    
    public void altera(Banca banca) throws SQLException;
    
    public void remove(Banca banca) throws SQLException;
    
    public Banca getBanca(Integer id) throws SQLException;
    
    public List<Banca> listaABanca() throws SQLException;
    
}
