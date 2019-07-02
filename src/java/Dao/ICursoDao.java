/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// */
package Dao;

import dominio.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public interface ICursoDao {
    
    
    public void adicionar(Curso curso) throws SQLException;
    
    public void altera(Curso curso) throws SQLException;
    
    public void remove(Curso curso) throws SQLException;
    
    public Curso getCurso(Integer id) throws SQLException;
    
    public List<Curso> listaCurso() throws SQLException;
}
