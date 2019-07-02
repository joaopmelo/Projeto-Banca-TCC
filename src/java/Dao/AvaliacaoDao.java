/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Avaliacao;

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
public class AvaliacaoDao implements IAvaliacaoDao{

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    
    @Override
    public void adicionar(Avaliacao avaliacao) throws SQLException {
        
        conn = cf.getConnection();
        
        
            
            sql = "insert into avaliacao(nota,cod_banca,dataRealizacao,resultado)"
                    + "values(?,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            

            
            pstm.setDouble(1, avaliacao.getNota());
            pstm.setInt(2, avaliacao.getBanca().getId() );
            pstm.setString(3, avaliacao.getDataRealizacao());
            pstm.setString(4, avaliacao.getResultado());
           
            pstm.executeUpdate();
            
            
            
            // fecha as conexões
                 pstm.close();
                conn.close();
            
      
    }

    @Override
    public void altera(Avaliacao avaliacao) throws SQLException {
        
         conn = cf.getConnection();
        
       
            
        sql = "update avaliacao set cod_banca=?,nota=?,dataRealizacao=?,resultado=? where id_avaliacao=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, avaliacao.getBanca().getId());
        pstm.setDouble(2, avaliacao.getNota());
        pstm.setString(3, avaliacao.getDataRealizacao());
        pstm.setString(4, avaliacao.getResultado());
        pstm.setInt(5, avaliacao.getId());
                
        pstm.executeUpdate();
        
       
        // fecha as conexões
            pstm.close();
            conn.close();
            
       
        
    }

    @Override
    public void remove(Avaliacao avaliacao) throws SQLException {
       
        conn = cf.getConnection();
        
       
            
            sql = "delete from avaliacao where id_avaliacao=? ";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, avaliacao.getId());
            
            pstm.executeUpdate();
            
         
            pstm.close();
            conn.close();
            
       
    }

    @Override
    public Avaliacao getAvaliacao(Integer id) throws SQLException {
        conn = cf.getConnection();
        
        sql = "select * from avaliacao where id_avaliacao=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        Avaliacao avaliacao = new Avaliacao();
        BancaDao banca = new BancaDao();
        
        while (rs.next()) {
           
            // Populando o objeto avaliacao
            
            avaliacao.setId(rs.getInt("id_avaliacao"));
            avaliacao.setNota(rs.getDouble("nota"));
            avaliacao.setBanca(banca.getBanca(rs.getInt("cod_banca")));
            avaliacao.setDataRealizacao(rs.getString("dataRealizacao"));
            avaliacao.setResultado(rs.getString("resultado"));
           
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return avaliacao;
        
    }

    @Override
    public List<Avaliacao> listaAvaliacao() throws SQLException {
        
        List<Avaliacao> avaliacoes = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from avaliacao";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
        Avaliacao avaliacao = new Avaliacao();
        BancaDao banca = new BancaDao();
        
        while (rs.next()) {
           
            // Populando o objeto avaliacao
            
            avaliacao.setNota(rs.getDouble("nota"));
            avaliacao.setBanca(banca.getBanca(rs.getInt("cod_banca")));
            avaliacao.setDataRealizacao(rs.getString("dataRealizacao"));
            avaliacao.setResultado(rs.getString("resultado"));
            
            avaliacoes.add(avaliacao);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return avaliacoes;
        
    }
    
    
    
}
