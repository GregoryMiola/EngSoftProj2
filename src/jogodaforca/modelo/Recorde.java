/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodaforca.modelo;

/**
 *
 * @author Thiago
 */
public class Recorde {
    private long id;
    private String nome;
    private int pontos;

    public Recorde() {
        this.nome = "";
        this.pontos = 0;
    }

    public Recorde(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }
    
    public Recorde(long id, String nome, int pontos) {
        this.id = id;
    	this.nome = nome;
        this.pontos = pontos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

	
    
}
