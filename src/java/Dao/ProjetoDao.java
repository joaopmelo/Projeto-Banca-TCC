/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
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
public class ProjetoDao implements IProjetoDao {

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    
    
    
    @Override
    public void adicionar(Projeto projeto) throws SQLException {
        
        conn = cf.getConnection();
      


            
        // INSERINDO Projeto
    String sql = "insert into projeto(titulo,"
            + "cod_aluno,cod_curso,cod_tipo_projeto,"
            + "cod_pesquisa,cod_orientador) "
            + "VALUES (?,?,?,?,?,?)";
           
            pstm = conn.prepareStatement(sql);
            
            
            pstm.setString(1, projeto.getTitulo());
            pstm.setInt(2, projeto.getAluno().getId());
            pstm.setInt(3, projeto.getCurso().getId());
            pstm.setInt(4, projeto.getTipoProjeto().getId());
            pstm.setInt(5, projeto.getAreaPesquisa().getId());
            pstm.setInt(6, projeto.getOrientador().getId());

            pstm.executeUpdate();

            pstm.close();
            conn.close();

        
    }

    @Override
    public void altera(Projeto projeto) throws SQLException {
        
        conn = cf.getConnection();

        //desativar o commit implicito
        conn.setAutoCommit(false);

      

        try {


            // INSERINDO projeto
           String sql = "UPDATE projeto SET titulo=?,"
                    + "cod_aluno=?,cod_curso=?,"
                    + "cod_tipo_projeto=?,cod_pesquisa=?,"
                    + "cod_orientador=? WHERE id_projeto=?";
            pstm = conn.prepareStatement(sql);

            
            pstm.setString(1, projeto.getTitulo());
            pstm.setInt(2, projeto.getAluno().getId());
            pstm.setInt(3, projeto.getCurso().getId());
            pstm.setInt(4, projeto.getTipoProjeto().getId());
            pstm.setInt(5, projeto.getAreaPesquisa().getId());
            pstm.setInt(6, projeto.getOrientador().getId());
            pstm.setInt(7, projeto.getId());

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
    public void remove(Projeto projeto) throws SQLException {
        
        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "delete from projeto where id_projeto=?");

        pstm.setInt(1, projeto.getId());

        pstm.executeUpdate();
        pstm.close();
        conn.close();
    }

    @Override
    public Projeto getProjeto(Integer id) throws SQLException {
        
       conn = cf.getConnection();
       
       String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "where p.id_projeto=? \n" +   
            "order by p.id_projeto ASC";
       
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
        
        while (rs.next()) {
           
        
        
              // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
           
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projeto;
        
    }

    @Override
    public List<Projeto> listaProjeto() throws SQLException {
        
        
        
        conn = cf.getConnection();
        
      String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "order by p.id_projeto ASC";
        
        pstm = conn.prepareStatement(sql);

        rs = pstm.executeQuery();
        
        List<Projeto> projetos = new ArrayList<>();
        
        while (rs.next()) {            
            
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
              // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            projetos.add(projeto);
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projetos;
    }
    
    public List<Projeto> listaProjetoByAluno(String nomeAluno) throws SQLException {
        
        List<Projeto> projetos = new ArrayList<>();
        
        conn = cf.getConnection();
        
     
        
        String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "where a.nome ilike '%"+nomeAluno+"%' \n" +
            "order by p.id_projeto ASC";
       
        pstm = conn.prepareStatement(sql);
  
        
        rs = pstm.executeQuery();
        
        
        while (rs.next()) {            
            
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
             // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            projetos.add(projeto);
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projetos;
    }
    
    public List<Projeto> listaProjetoAreaPesquisa(String nomePesquisa) throws SQLException {
        
        List<Projeto> projetos = new ArrayList<>();
        
        conn = cf.getConnection();
        
        
        String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "where pes.nome ilike '%"+nomePesquisa+"%' \n" +
            "order by p.id_projeto ASC";
        
        pstm = conn.prepareStatement(sql);
  
        
        rs = pstm.executeQuery();
        
        
        while (rs.next()) {            
            
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
             // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            projetos.add(projeto);
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projetos;
    }
    
    public List<Projeto> listaProjetoTitulo(String nomeTitulo) throws SQLException {
        
        List<Projeto> projetos = new ArrayList<>();
        
        conn = cf.getConnection();
        
        
        String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "where p.titulo ilike '%"+nomeTitulo+"%' " +
            "order by p.id_projeto ASC";
        
        pstm = conn.prepareStatement(sql);
  
        
        rs = pstm.executeQuery();
        
        
        while (rs.next()) {            
            
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
             // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            projetos.add(projeto);
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projetos;
    }
    
    public List<Projeto> listaProjetoTipoProjeto(int idTipo) throws SQLException {
        
        List<Projeto> projetos = new ArrayList<>();
        
        conn = cf.getConnection();
        
        
       String sql="select *, ori.id_professor as p1_id, ori.nome as p1_nome, ori.cpf as p1_cpf, ori.rg as p1_rg, \n" +
            "ori.email as p1_email, ori.registro as p1_registro, ori.salario as p1_salario , \n" +
            "a.nome as nome_aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula as matricula_aluno, \n" +
            "c.nome as nome_curso, t.descricao as desc_tipo, pes.nome as nome_area_pesquisa, ori.nome as nome_orientador\n" +
            "from projeto p \n" +
            "inner join aluno a on (p.cod_aluno=a.id_aluno) \n" +
            "inner join curso c on (p.cod_curso=c.id_curso) \n" +
            "inner join tipo_projeto t on (p.cod_tipo_projeto=t.id_tipo) \n" +
            "inner join area_pesquisa pes on (p.cod_pesquisa=pes.id_pesquisa) \n" +
            "inner join professor ori on (p.cod_orientador=ori.id_professor) \n" +
            "where t.id_tipo=? " +
            "order by p.id_projeto ASC";
       
        pstm = conn.prepareStatement(sql);
  
         pstm.setInt(1, idTipo);
        
        rs = pstm.executeQuery();
        
        
        while (rs.next()) {            
            
        Projeto projeto = new Projeto();
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        TipoProjeto tprojeto = new TipoProjeto();
        AreaPesquisa areaPesquisa = new AreaPesquisa();
        Professor orientador = new Professor();
        
             // criando o objeto Aluno
           
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            // Populando o objeto curso
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
             // Populando o objeto tipoProjeto
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            // Populando o objeto areaPesquisa
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            // criando o objeto Professor
            orientador.setId(rs.getInt("p1_id"));
            orientador.setNome(rs.getString("p1_nome"));
            orientador.setCpf(rs.getString("p1_cpf"));
            orientador.setRg(rs.getString("p1_rg"));
            orientador.setEmail(rs.getString("p1_email"));
            orientador.setRegistro(rs.getInt("p1_registro"));
            orientador.setSalario(rs.getDouble("p1_salario"));
            
             // Populando o objeto Projeto
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            projetos.add(projeto);
            
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return projetos;
    }
    
    
    
}
