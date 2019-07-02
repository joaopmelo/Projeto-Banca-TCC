/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.AreaPesquisa;
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
public class AreaDePesquisaDao implements IAreaDePesquidaDao{

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    
    @Override
    public void adicionar(AreaPesquisa areaPesquisa) throws SQLException {
        
        conn = cf.getConnection();
        
        // desativando o commit
        conn.setAutoCommit(false);
        
        
        
        try {
            
            sql = "insert into area_pesquisa(nome)"
                    + "values(?)";
            
            pstm = conn.prepareStatement(sql);
            
            
            pstm.setString(1, areaPesquisa.getNome());
          
            
            pstm.executeUpdate();
            
            // confirma
                conn.commit();
            
            // fecha as conexões
                 pstm.close();
                conn.close();
            
        } catch (Exception e) {
            
            // desfazer
                conn.rollback();
            
             // fecha as conexões
                pstm.close();
                conn.close();
            
        }
    }

    @Override
    public void altera(AreaPesquisa areaPesquisa) throws SQLException {
         conn = cf.getConnection();
        
        // desativando o commit
            conn.setAutoCommit(false);
      
        try {
            
            sql = "update area_pesquisa set nome=? where id_pesquisa=?";
        
        pstm = conn.prepareStatement(sql);
        
       
        pstm.setString(1, areaPesquisa.getNome());
        pstm.setInt(2, areaPesquisa.getId());
        
        pstm.executeUpdate();
        
        // confirma
            conn.commit();
        
        // fecha as conexões
            pstm.close();
            conn.close();
            
        } catch (Exception e) {
        // desfaz   
            conn.rollback();
        
        // fecha as conexões
            pstm.close();
            conn.close();
        }
        
    }

    @Override
    public void remove(AreaPesquisa areaPesquisa) throws SQLException {
        
         conn = cf.getConnection();
        
        conn.setAutoCommit(false);
        
        try {
            
            sql = "delete from area_pesquisa where id_pesquisa=? ";
            
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, areaPesquisa.getId());
            
            conn.commit();
            
            pstm.close();
            conn.close();
            
        } catch (Exception e) {
            conn.rollback();
            
            pstm.close();
            conn.close();
        }
    }
    

    @Override
    public AreaPesquisa getAreaPesquisa(Integer id) throws SQLException {
        conn = cf.getConnection();
        
        sql = "select * from area_pesquisa where id_pesquisa=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        
        while (rs.next()) {
           
            // Populando o objeto areaPesquisa
            
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome"));
            
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return areaPesquisa;
        
        
    }

    @Override
    public List<AreaPesquisa> listaAreaPesquisa() throws SQLException {
         List<AreaPesquisa> areaPesquisas = new ArrayList<>();
        
        conn = cf.getConnection();
        
        sql = "select * from area_pesquisa";
        
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        
        
        
        while (rs.next()) {
            
            AreaPesquisa areaPesquisa = new AreaPesquisa();
           
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome"));
            
            areaPesquisas.add(areaPesquisa);
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return areaPesquisas;
       
    }
    
    
    
    
}
