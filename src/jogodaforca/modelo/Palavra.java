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
public class Palavra {
    private String palavra;
    private String tema;
    
    public Palavra(){
        this.palavra = "";
        this.tema = "";
    }

    public Palavra(String palavra, String tema) {
        this.palavra = palavra;
        this.tema = tema;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
}
