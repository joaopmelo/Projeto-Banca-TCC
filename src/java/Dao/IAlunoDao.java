/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import dominio.Aluno;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Pedro
 */
public interface IAlunoDao {
    
    public void adiciona(Aluno aluno) throws SQLException;

    public void altera(Aluno aluno) throws SQLException;

    public void remove(Aluno aluno) throws SQLException;

    public Aluno getAluno(Integer id) throws SQLException;

    public List<Aluno> listaAlunos() throws SQLException;
}
