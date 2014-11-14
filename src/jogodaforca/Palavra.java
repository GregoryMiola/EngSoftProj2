/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodaforca;

/**
 *
 * @author Thiago
 */
public class Palavra {
    private String palavra;
    private boolean posicoes_visiveis[];
    
    public int LetraExiste(char c){
        int i,l=0;
        for(i=0;i<=palavra.length()-1;i++){
            if(c==palavra.charAt(i)){
                posicoes_visiveis[i] = true;
                l++;
            }
        }
        return l;
    }

    public Palavra(String palavra) {
        this.palavra = palavra;
        inicializaPosicoes();
    }

    public String getPalavra() {
        return palavra;
    }

    /*public void setPalavra(String palavra) {
        this.palavra = palavra;
    }*/
    public String getLetra(int pos){
        return this.palavra.substring(pos, pos+1);
    }
    public int tamanho(){
        return this.palavra.length();
    }
    public boolean getPosicao(int pos){
        return posicoes_visiveis[pos];
    }
    private void inicializaPosicoes(){
        this.posicoes_visiveis = new boolean[palavra.length()];
        
        for(int i = 0;i<posicoes_visiveis.length-1;i++){
            posicoes_visiveis[i] = false;
        }
    }
    
    public boolean comparaPalavra(String outraPalavra){
        return this.palavra.compareToIgnoreCase(outraPalavra)==0;
    }
}
