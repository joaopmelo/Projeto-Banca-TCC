/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import dominio.Situacao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface ISituacaoDao {
    
    public void adicionar(Situacao situacao) throws SQLException;
    
    public void altera(Situacao situacao) throws SQLException;
    
    public void remove(Situacao situacao) throws SQLException;
    
    public Situacao getSituacao(Integer id) throws SQLException;
    
    public List<Situacao> listaSituacao() throws SQLException;
    
}
