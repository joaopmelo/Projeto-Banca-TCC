/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author jpedro
 */
public class Projeto {
    
    private int id;
    private String titulo;
    private Aluno aluno;
    private Curso curso;
    private TipoProjeto tipoProjeto;
    private AreaPesquisa areaPesquisa;
    private Professor Orientador;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    public AreaPesquisa getAreaPesquisa() {
        return areaPesquisa;
    }

    public void setAreaPesquisa(AreaPesquisa areaPesquisa) {
        this.areaPesquisa = areaPesquisa;
    }

    public Professor getOrientador() {
        return Orientador;
    }

    public void setOrientador(Professor Orientador) {
        this.Orientador = Orientador;
    }
    
}
