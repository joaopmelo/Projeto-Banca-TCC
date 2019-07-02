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
public class Banca {
    
    private int id;
    private Projeto projeto;
    private Professor avaliador1;
    private Professor avaliador2;
    private Situacao situacao;
    

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Professor getAvaliador1() {
        return avaliador1;
    }

    public void setAvaliador1(Professor avaliador1) {
        this.avaliador1 = avaliador1;
    }

    public Professor getAvaliador2() {
        return avaliador2;
    }

    public void setAvaliador2(Professor avaliador2) {
        this.avaliador2 = avaliador2;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
