/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dao.ProjetoDao;
import dominio.Projeto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpedro
 */
public class Service_projeto {
    
    ProjetoDao projDao = new ProjetoDao();

    public void adicionaProjeto(Projeto projeto) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        projDao.adicionar(projeto);//

    }

    public void alteraProjeto(Projeto projeto) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        projDao.altera(projeto);//

    }

    public Projeto buscaPorIdProjeto(Integer id) throws SQLException {

        //validar regras
        //salvar no BD
        Projeto projeto = projDao.getProjeto(id);

        return projeto;

    }
    
    public List<Projeto> ListaProjetoTitulo(String nome_titulo) throws SQLException {

        List<Projeto> projetos = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (nome_titulo==null) {
            
            projetos = projDao.listaProjeto();
        }
        else{
            projetos = projDao.listaProjetoTitulo(nome_titulo);
        }
        

        return projetos;

    }
    public List<Projeto> ListaProjetoArea(String nome_area) throws SQLException {

        List<Projeto> projetos = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (nome_area==null) {
            
            projetos = projDao.listaProjeto();
        }
        else{
            projetos = projDao.listaProjetoAreaPesquisa(nome_area);
        }
        

        return projetos;

    }
    public List<Projeto> ListaProjetoTipo(int idTipo) throws SQLException {

        List<Projeto> projetos = new ArrayList<>();
                
        //validar regras
        //salvar no BD
        if (idTipo==0) {
            
            projetos = projDao.listaProjeto();
        }
        else{
            projetos = projDao.listaProjetoTipoProjeto(idTipo);
        }
        

        return projetos;

    }
    
    public void removeProjeto(Projeto projeto) throws SQLException {

        //validar regras
        //salvar no BD
        //salvando o projeto
        projDao.remove(projeto);//

    }
}
