/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.TipoProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpedro
 */
public class TipoProjetoDao implements ITipoProjetoDao{
    
    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    

    @Override
    public void adicionar(TipoProjeto tProjeto) throws SQLException {
        conn = cf.getConnection();
        //desativar o commit implicito
        conn.setAutoCommit(false);
        String sql;

        try {

            
        // INSERINDO EMPREGADO
            sql = "Insert into tipo_projeto(descricao) "
                    + "VALUES (?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, tProjeto.getDescricao());
           

            pstm.executeUpdate();
//confirma a operação
            conn.commit();

            pstm.close();
            conn.close();
        } catch (SQLException ex) {
//desfaz a operação
            conn.rollback();
            pstm.close();
            conn.close();

        }
    }

    @Override
    public void altera(TipoProjeto tProjeto) throws SQLException {
        
        conn = cf.getConnection();

        //desativar o commit implicito
        conn.setAutoCommit(false);

        String sql;

        try {


            // INSERINDO EMPREGADO
            sql = "UPDATE tipo_projeto SET descricao=? WHERE id_tipo=?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, tProjeto.getDescricao());
            pstm.setInt(2, tProjeto.getId());


            pstm.executeUpdate();

            conn.commit();

            pstm.close();
            conn.close();
        } catch (SQLException ex) {

            conn.rollback();
            pstm.close();
            conn.close();

        }
    }

    @Override
    public void remove(TipoProjeto tProjeto) throws SQLException {
        
        conn = cf.getConnection();

        pstm = conn.prepareStatement("delete from tipo_projeto where id_tipo=?");

        pstm.setInt(1, tProjeto.getId());

        pstm.executeUpdate();
        pstm.close();
        conn.close();
    }

    @Override
    public TipoProjeto getTipoProjeto(Integer id) throws SQLException {
        conn = cf.getConnection();

        String sql = "select * from tipo_projeto"
                + " where id_tipo=?";

        pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        rs = pstm.executeQuery();

        //declarando os objetos
        TipoProjeto tProjeto = new TipoProjeto();
       

        while (rs.next()) {

            // setando os atributos do objeto Empregado
            tProjeto.setId(rs.getInt("id_tipo"));
            tProjeto.setDescricao(rs.getString("descricao"));
           
           

        }

        rs.close();
        pstm.close();
        conn.close();

        return tProjeto;
        
    }

    @Override
    public List<TipoProjeto> listaTipoProjeto() throws SQLException {
       
        List<TipoProjeto> tProjetos = new ArrayList<>();

        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "select * from tipo_projeto");

        rs = pstm.executeQuery();

        while (rs.next()) {
            // criando o objeto TipoProjeto
            TipoProjeto tProjeto = new TipoProjeto();
            tProjeto.setId(rs.getInt("id_tipo"));
            tProjeto.setDescricao(rs.getString("descricao"));
            
            //adiciona o objeto na lista
            tProjetos.add(tProjeto);
        }
        rs.close();
        pstm.close();
        conn.close();

        return tProjetos;
        
    }
    
}
