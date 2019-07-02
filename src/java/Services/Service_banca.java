/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dao.BancaDao;
import dominio.Banca;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpedro
 */
public class Service_banca {
    
     BancaDao bancaDao = new BancaDao();

    public void adicionaBanca(Banca banca) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        bancaDao.adicionar(banca);//

    }

    public void alteraBanca(Banca banca) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        bancaDao.altera(banca);//

    }

    public Banca buscaPorIdBanca(Integer id) throws SQLException {

        //validar regras
        //salvar no BD
        Banca banca = bancaDao.getBanca(id);

        return banca;

    }
    
    public List<Banca> ListaBancaTitulo(String nome_titulo) throws SQLException {

        List<Banca> projetos = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (nome_titulo==null) {
            
            projetos = bancaDao.listaBanca();
        }
        else{
            projetos = bancaDao.listaBancaTitulo(nome_titulo);
        }
        

        return projetos;

    }
    public List<Banca> ListaBancaArea(String nome_area) throws SQLException {

        List<Banca> bancas = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (nome_area==null) {
            
            bancas = bancaDao.listaBanca();
        }
        else{
            bancas = bancaDao.listaBancaAreaPesquisa(nome_area);
        }
        

        return bancas;

    }
    public List<Banca> ListaBancaTipo(int idTipo) throws SQLException {

        List<Banca> bancas = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (idTipo==0) {
            
            bancas = bancaDao.listaBanca();
        }
        else{
            bancas = bancaDao.listaBancaTipoBanca(idTipo);
        }
        

        return bancas;

    }
    
    public void removeBanca(Banca banca) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        bancaDao.remove(banca);//

    }
}
