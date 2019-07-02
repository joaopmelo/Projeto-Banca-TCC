/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DB.ConnectionFactoryDs;
import dominio.Aluno;
import dominio.AreaPesquisa;
import dominio.Banca;
import dominio.Curso;
import dominio.Professor;
import dominio.Projeto;
import dominio.Situacao;
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
public class BancaDao implements IBancaDao{

    private ConnectionFactoryDs cf = new ConnectionFactoryDs();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    String sql;
    
    @Override
    public void adicionar(Banca banca) throws SQLException {
        
        conn = cf.getConnection();
        
        String sql;

            
        // INSERINDO EMPREGADO
            sql = "Insert into banca(cod_projeto,cod_avaliador1,cod_avaliador2,cod_situacao) "
                    + "VALUES (?,?,?,?)";
           
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, banca.getProjeto().getId());
            pstm.setInt(2, banca.getAvaliador1().getId());
            pstm.setInt(3, banca.getAvaliador2().getId());
            pstm.setInt(4, banca.getSituacao().getId());
            
           
   

            pstm.executeUpdate();


            pstm.close();
            conn.close();
   
    }

    @Override
    public void altera(Banca banca) throws SQLException {
        
        conn = cf.getConnection();

       

        String sql;

        


            // INSERINDO projeto
            sql = "UPDATE banca SET cod_projeto=?,cod_avaliador1=?,cod_avaliador2=?,cod_situacao=? WHERE id_banca=?";
            pstm = conn.prepareStatement(sql);

            
            pstm.setInt(1, banca.getProjeto().getId());
            pstm.setInt(2, banca.getAvaliador1().getId());
            pstm.setInt(3, banca.getAvaliador2().getId());
            pstm.setInt(4, banca.getSituacao().getId());
            pstm.setInt(5, banca.getId());

            pstm.executeUpdate();

           

            pstm.close();
            conn.close();
       
    }

    @Override
    public void remove(Banca banca) throws SQLException {
        
         conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "delete from banca where id_banca=?");

        pstm.setInt(1, banca.getId());

        pstm.executeUpdate();
        pstm.close();
        conn.close();

    }

    @Override
    public Banca getBanca(Integer id) throws SQLException {
        conn = cf.getConnection();
        
        sql = "select * from banca where id_banca=?";
        
        pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        rs = pstm.executeQuery();
        
        Banca banca = new Banca();
        ProjetoDao proj = new ProjetoDao();
        ProfessorDao prof1 = new ProfessorDao();
        ProfessorDao prof2 = new ProfessorDao();
        SituacaoDao situacao = new SituacaoDao();
        
        
        while (rs.next()) {
           
            // Populando o objeto banca
            
            banca.setId(rs.getInt("id_banca"));
            banca.setProjeto(proj.getProjeto(rs.getInt("cod_projeto")));
            banca.setAvaliador1(prof1.getProfessor(rs.getInt("cod_avaliador1")));
            banca.setAvaliador2(prof2.getProfessor(rs.getInt("cod_avaliador2")));
            banca.setSituacao(situacao.getSituacao(rs.getInt("cod_situacao")));
           
           
        }
        
        rs.close();
        pstm.close();
        conn.close();
        
        return banca;
        
    }

    @Override
    public List<Banca> listaABanca() throws SQLException {
        List<Banca> bancas = new ArrayList<>();

        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "select *, p1.id_professor as p1_id, p1.nome as p1_nome, p1.cpf as p1_cpf, p1.rg as p1_rg, "
                        + "p1.email as p1_email, p1.registro as p1_registro, p1.salario as p1_salario , "
                        + "p2.id_professor as p2_id, p2.nome as p2_nome, p2.cpf as p2_cpf, p2.rg as p2_rg, "
                        + "p2.email as p2_email, p2.registro as p2_registro, p2.salario as p2_salario, "
                        + "al.nome as nome_aluno, al.cpf as cpf_aluno, al.rg as rg_aluno, al.email as email_aluno, al.matricula as matricula_aluno, "
                        + "c.nome as nome_curso, tp.descricao as desc_tipo, ap.nome as nome_area_pesquisa, ori.nome as nome_orientador, "
                        + "ori.id_professor as id_orientador, ori.registro as registro_orientador, ori.salario as salario_orientador, ori.cpf as cpf_orientador, "
                        + "ori.rg as rg_orientador, ori.email as email_orientador "
                        + "from banca b "
                        + "inner join professor p1 on (b.cod_avaliador1=p1.id_professor) "
                        + "inner join professor p2 on (b.cod_avaliador2=p2.id_professor) "
                        + "inner join situacao s on (b.cod_situacao=s.id_situacao) "
                        + "inner join projeto p on (b.cod_projeto=p.id_projeto) "
                        + "inner join aluno al on (p.cod_aluno=al.id_aluno) "
                        + "inner join curso c on (p.cod_curso=c.id_curso) "
                        + "inner join tipo_projeto tp on (p.cod_tipo_projeto=tp.id_tipo) "
                        + "inner join area_pesquisa ap on (p.cod_pesquisa=ap.id_pesquisa) "
                        + "inner join professor ori on (p.cod_orientador=ori.id_professor) "
                        + "order by id_banca ASC"
        
        );

        rs = pstm.executeQuery();

        while (rs.next()) {
            
            
            Banca banca = new Banca();
            Projeto projeto = new Projeto();
            Professor prof1 = new Professor();
            Professor prof2 = new Professor();
            Situacao situacao = new Situacao();
            Aluno aluno = new Aluno();
            Curso curso = new Curso();
            TipoProjeto tprojeto = new TipoProjeto();
            Professor orientador = new Professor();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
        
            // Populando avaliador 1
            prof1.setId(rs.getInt("p1_id"));
            prof1.setNome(rs.getString("p1_nome"));
            prof1.setCpf(rs.getString("p1_cpf"));
            prof1.setRg(rs.getString("p1_rg"));
            prof1.setEmail(rs.getString("p1_email"));
            prof1.setRegistro(rs.getInt("p1_registro"));
            prof1.setSalario(rs.getDouble("p1_salario"));
            
            // Populando avaliador 1
            prof2.setId(rs.getInt("p2_id"));
            prof2.setNome(rs.getString("p2_nome"));
            prof2.setCpf(rs.getString("p2_cpf"));
            prof2.setRg(rs.getString("p2_rg"));
            prof2.setEmail(rs.getString("p2_email"));
            prof2.setRegistro(rs.getInt("p2_registro"));
            prof2.setSalario(rs.getDouble("p2_salario"));
            
            
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            orientador.setId(rs.getInt("id_orientador"));
            orientador.setNome(rs.getString("nome_orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail(rs.getString("email_orientador"));
            orientador.setRegistro(rs.getInt("registro_orientador"));
            orientador.setSalario(rs.getDouble("salario_orientador"));
            
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            
            // Populando situação
            situacao.setId(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
            
            // Populando banca
            banca.setId(rs.getInt("id_banca"));
            banca.setProjeto(projeto);
            banca.setAvaliador1(prof1);
            banca.setAvaliador2(prof2);
            banca.setSituacao(situacao);
            
            //adiciona o objeto na lista
            bancas.add(banca);
        }
        rs.close();
        pstm.close();
        conn.close();

        return bancas;
    
        
    }
    
    public List<Banca> listaBancaFinalizadas(Banca bf) throws SQLException {
        List<Banca> bancas = new ArrayList<>();

        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "select *, p1.id_professor as p1_id, p1.nome as p1_nome, p1.cpf as p1_cpf, p1.rg as p1_rg, "
                        + "p1.email as p1_email, p1.registro as p1_registro, p1.salario as p1_salario , "
                        + "p2.id_professor as p2_id, p2.nome as p2_nome, p2.cpf as p2_cpf, p2.rg as p2_rg, "
                        + "p2.email as p2_email, p2.registro as p2_registro, p2.salario as p2_salario, "
                        + "al.nome as nome_aluno, al.cpf as cpf_aluno, al.rg as rg_aluno, al.email as email_aluno, al.matricula as matricula_aluno, "
                        + "c.nome as nome_curso, tp.descricao as desc_tipo, ap.nome as nome_area_pesquisa, ori.nome as nome_orientador, "
                        + "ori.id_professor as id_orientador, ori.registro as registro_orientador, ori.salario as salario_orientador, ori.cpf as cpf_orientador, "
                        + "ori.rg as rg_orientador, ori.email as email_orientador "
                        + "from banca b "
                        + "inner join professor p1 on (b.cod_avaliador1=p1.id_professor) "
                        + "inner join professor p2 on (b.cod_avaliador2=p2.id_professor) "
                        + "inner join situacao s on (b.cod_situacao=s.id_situacao) "
                        + "inner join projeto p on (b.cod_projeto=p.id_projeto) "
                        + "inner join aluno al on (p.cod_aluno=al.id_aluno) "
                        + "inner join curso c on (p.cod_curso=c.id_curso) "
                        + "inner join tipo_projeto tp on (p.cod_tipo_projeto=tp.id_tipo) "
                        + "inner join area_pesquisa ap on (p.cod_pesquisa=ap.id_pesquisa) "
                        + "inner join professor ori on (p.cod_orientador=ori.id_professor) "
                        + "where b.cod_situacao=1"
                        + "order by id_banca ASC"
        
        );


        rs = pstm.executeQuery();

        while (rs.next()) {
            
            
            Banca banca = new Banca();
            Projeto projeto = new Projeto();
            Professor prof1 = new Professor();
            Professor prof2 = new Professor();
            Situacao situacao = new Situacao();
            Aluno aluno = new Aluno();
            Curso curso = new Curso();
            TipoProjeto tprojeto = new TipoProjeto();
            Professor orientador = new Professor();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
        
            // Populando avaliador 1
            prof1.setId(rs.getInt("p1_id"));
            prof1.setNome(rs.getString("p1_nome"));
            prof1.setCpf(rs.getString("p1_cpf"));
            prof1.setRg(rs.getString("p1_rg"));
            prof1.setEmail(rs.getString("p1_email"));
            prof1.setRegistro(rs.getInt("p1_registro"));
            prof1.setSalario(rs.getDouble("p1_salario"));
            
            // Populando avaliador 1
            prof2.setId(rs.getInt("p2_id"));
            prof2.setNome(rs.getString("p2_nome"));
            prof2.setCpf(rs.getString("p2_cpf"));
            prof2.setRg(rs.getString("p2_rg"));
            prof2.setEmail(rs.getString("p2_email"));
            prof2.setRegistro(rs.getInt("p2_registro"));
            prof2.setSalario(rs.getDouble("p2_salario"));
            
            
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            orientador.setId(rs.getInt("id_orientador"));
            orientador.setNome(rs.getString("nome_orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail(rs.getString("email_orientador"));
            orientador.setRegistro(rs.getInt("registro_orientador"));
            orientador.setSalario(rs.getDouble("salario_orientador"));
            
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            
            // Populando situação
            situacao.setId(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
            
            // Populando banca
            banca.setId(rs.getInt("id_banca"));
            banca.setProjeto(projeto);
            banca.setAvaliador1(prof1);
            banca.setAvaliador2(prof2);
            banca.setSituacao(situacao);
            
            //adiciona o objeto na lista
            bancas.add(banca);
        }
        rs.close();
        pstm.close();
        conn.close();

        return bancas;
    
        
    }
    public List<Banca> listaBancaNaoFinalizadas(Banca bf) throws SQLException {
        List<Banca> bancas = new ArrayList<>();

        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "select *, p1.id_professor as p1_id, p1.nome as p1_nome, p1.cpf as p1_cpf, p1.rg as p1_rg, "
                        + "p1.email as p1_email, p1.registro as p1_registro, p1.salario as p1_salario , "
                        + "p2.id_professor as p2_id, p2.nome as p2_nome, p2.cpf as p2_cpf, p2.rg as p2_rg, "
                        + "p2.email as p2_email, p2.registro as p2_registro, p2.salario as p2_salario, "
                        + "al.nome as nome_aluno, al.cpf as cpf_aluno, al.rg as rg_aluno, al.email as email_aluno, al.matricula as matricula_aluno, "
                        + "c.nome as nome_curso, tp.descricao as desc_tipo, ap.nome as nome_area_pesquisa, ori.nome as nome_orientador, "
                        + "ori.id_professor as id_orientador, ori.registro as registro_orientador, ori.salario as salario_orientador, ori.cpf as cpf_orientador, "
                        + "ori.rg as rg_orientador, ori.email as email_orientador "
                        + "from banca b "
                        + "inner join professor p1 on (b.cod_avaliador1=p1.id_professor) "
                        + "inner join professor p2 on (b.cod_avaliador2=p2.id_professor) "
                        + "inner join situacao s on (b.cod_situacao=s.id_situacao) "
                        + "inner join projeto p on (b.cod_projeto=p.id_projeto) "
                        + "inner join aluno al on (p.cod_aluno=al.id_aluno) "
                        + "inner join curso c on (p.cod_curso=c.id_curso) "
                        + "inner join tipo_projeto tp on (p.cod_tipo_projeto=tp.id_tipo) "
                        + "inner join area_pesquisa ap on (p.cod_pesquisa=ap.id_pesquisa) "
                        + "inner join professor ori on (p.cod_orientador=ori.id_professor) "
                        + "where b.cod_situacao=2"
                        + "order by id_banca ASC"
        
        );


        rs = pstm.executeQuery();

        while (rs.next()) {
            
            
            Banca banca = new Banca();
            Projeto projeto = new Projeto();
            Professor prof1 = new Professor();
            Professor prof2 = new Professor();
            Situacao situacao = new Situacao();
            Aluno aluno = new Aluno();
            Curso curso = new Curso();
            TipoProjeto tprojeto = new TipoProjeto();
            Professor orientador = new Professor();
            AreaPesquisa areaPesquisa = new AreaPesquisa();
        
            // Populando avaliador 1
            prof1.setId(rs.getInt("p1_id"));
            prof1.setNome(rs.getString("p1_nome"));
            prof1.setCpf(rs.getString("p1_cpf"));
            prof1.setRg(rs.getString("p1_rg"));
            prof1.setEmail(rs.getString("p1_email"));
            prof1.setRegistro(rs.getInt("p1_registro"));
            prof1.setSalario(rs.getDouble("p1_salario"));
            
            // Populando avaliador 1
            prof2.setId(rs.getInt("p2_id"));
            prof2.setNome(rs.getString("p2_nome"));
            prof2.setCpf(rs.getString("p2_cpf"));
            prof2.setRg(rs.getString("p2_rg"));
            prof2.setEmail(rs.getString("p2_email"));
            prof2.setRegistro(rs.getInt("p2_registro"));
            prof2.setSalario(rs.getDouble("p2_salario"));
            
            
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome_aluno"));
            aluno.setCpf(rs.getString("cpf_aluno"));
            aluno.setRg(rs.getString("rg_aluno"));
            aluno.setEmail(rs.getString("email_aluno"));
            aluno.setMatricula(rs.getInt("matricula_aluno"));
            
            curso.setId(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nome_curso"));
            curso.setInstituicao(rs.getString("instituicao"));
            curso.setCargaHoraria(rs.getInt("carga_horaria"));
            
            tprojeto.setId(rs.getInt("id_tipo"));
            tprojeto.setDescricao(rs.getString("desc_tipo"));
            
            areaPesquisa.setId(rs.getInt("id_pesquisa"));
            areaPesquisa.setNome(rs.getString("nome_area_pesquisa"));
            
            orientador.setId(rs.getInt("id_orientador"));
            orientador.setNome(rs.getString("nome_orientador"));
            orientador.setCpf(rs.getString("cpf_orientador"));
            orientador.setRg(rs.getString("rg_orientador"));
            orientador.setEmail(rs.getString("email_orientador"));
            orientador.setRegistro(rs.getInt("registro_orientador"));
            orientador.setSalario(rs.getDouble("salario_orientador"));
            
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setTitulo(rs.getString("titulo"));
            projeto.setAluno(aluno);
            projeto.setCurso(curso);
            projeto.setTipoProjeto(tprojeto);
            projeto.setAreaPesquisa(areaPesquisa);
            projeto.setOrientador(orientador);
            
            
            // Populando situação
            situacao.setId(rs.getInt("id_situacao"));
            situacao.setDescricao(rs.getString("descricao"));
            
            // Populando banca
            banca.setId(rs.getInt("id_banca"));
            banca.setProjeto(projeto);
            banca.setAvaliador1(prof1);
            banca.setAvaliador2(prof2);
            banca.setSituacao(situacao);
            
            //adiciona o objeto na lista
            bancas.add(banca);
        }
        rs.close();
        pstm.close();
        conn.close();

        return bancas;
    
        
    }
    
    
}
