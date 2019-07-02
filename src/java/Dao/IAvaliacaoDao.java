/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import dominio.Avaliacao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface IAvaliacaoDao {
    
    public void adicionar(Avaliacao avaliacao) throws SQLException;
    
    public void altera(Avaliacao avaliacao) throws SQLException;
    
    public void remove(Avaliacao avaliacao) throws SQLException;
    
    public Avaliacao getAvaliacao(Integer id) throws SQLException;
    
    public List<Avaliacao> listaAvaliacao() throws SQLException;
}
