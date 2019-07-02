/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Situacao;
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
public class SituacaoDao implements ISituacaoDao{
    
    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    @Override
    public void adicionar(Situacao situacao) throws SQLException {
       
        conn = cf.getConnection();
        
        
            
            sql = "insert into situacao(descricao)"
                    + "values(?)";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, situacao.getDescricao());
            
            pstm.executeUpdate();
            
          
            
            // fecha as conexões
                pstm.close();
                conn.close();
            
       
        
    }

    @Override
    public void altera(Situacao situacao) throws SQLException {
        
        conn = cf.getConnection();
        
    
            
            sql = "update situacao set descricao=? where id_situacao = ?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, situacao.getDescricao());
        pstm.setInt(2, situacao.getId());
        
        pstm.executeUpdate();
        
        
        // fecha as conexões
            pstm.close();
            conn.close();
            
      
        
        
    }

    @Override
    public void remove(Situacao situacao) throws SQLException {
        conn = cf.getConnection();
        
        
            sql = "delete from situacao where id_situacao=? ";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, situacao.getId());
            
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            
       
    }

    @Override
    public Situacao getSituacao(Integer id) throws SQLException {
        
        conn = cf.getConnection();
        
        sql = "select * from situacao where id_situacao=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        Situacao situacao = new Situacao();
        
        while (rs.next()) {
           
            // Populando o objeto situacao
            
            situacao.setId(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
           
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return situacao;
        
    }

    @Override
    public List<Situacao> listaSituacao() throws SQLException {
        
        List<Situacao> situacoes = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from situacao";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
        Situacao situacao = new Situacao();
        
        while (rs.next()) {
           
            // Populando o objeto curso
            
            situacao.setDescricao(rs.getString("descricao"));
            
            situacoes.add(situacao);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return situacoes;
       
    }

   

}
