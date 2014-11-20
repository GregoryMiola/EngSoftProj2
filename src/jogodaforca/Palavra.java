/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodaforca;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class Palavra {
    private String palavra;
    private String tema;
    private int erros;
    private int max_erros;
    private char letras_erradas[];
    private boolean posicoes_visiveis[];
    private boolean fim_jogo = false;
    private boolean vitoria = false;
    
    public int chutarLetra(char c){
        int i,l=0;
        for(i=0;i<=palavra.length()-1;i++){
            if(c==palavra.charAt(i)){
                posicoes_visiveis[i] = true;
                l++;
            }
        }
        if(l==0){
            verificaLetraErrada(c);
        }else{
            calcularVitoria();
        }
        return l;
    }

    public Palavra(String palavra,String tema,int total_erros) {
        int i;
        this.palavra = palavra;
        this.tema = tema;
        this.max_erros = total_erros;
        this.posicoes_visiveis = new boolean[palavra.length()];
        this.letras_erradas = new char[total_erros];
        erros = 0;
        for(i = 0;i<posicoes_visiveis.length-1;i++){
            posicoes_visiveis[i] = false;
        }
        for(i=0;i<total_erros;i++){
            letras_erradas[i] = '*';
        }
    }

    public String getPalavra() {
        return palavra;
    }
    
    public String getLetrasErradas(){
        String str = letras_erradas[0]+"";
        int i;
        for(i=1;i<erros;i++){
            str = str + " - " + letras_erradas[i];
        }
        return str;
    }
    
    public int getErros(){
        return erros;
    }
    
    public String getTema(){
        return tema;
    }

    public String getLetra(int pos){
        return this.palavra.substring(pos, pos+1);
    }
    public int tamanho(){
        return this.palavra.length();
    }
    public boolean isFimDeJogo(){
        return fim_jogo;
    }
    public boolean isVitorioso(){
        return vitoria;
    }
    public boolean getPosicao(int pos){
        return posicoes_visiveis[pos];
    }

    public int getAcertoDeLetras(){
        int i,a=0;
        for(i=0;i<palavra.length();i++){
            if(posicoes_visiveis[i])a++;
        }
        return a;
    }
    private void verificaLetraErrada(char c){
        int i;
        boolean v = false;
        for(i=0;i<erros;i++){
            if(letras_erradas[i]==c) v = true;
        }
        if(v){
            JOptionPane.showMessageDialog(null, "A letra '"+c+"' já foi chutada!","Chute inválido" , JOptionPane.INFORMATION_MESSAGE);
        }else{
            erros++;
            letras_erradas[erros-1] = c;
            if(erros==max_erros)fim_jogo= true;
        }
        
    }
    public boolean comparaPalavra(String outraPalavra){
        return this.palavra.compareToIgnoreCase(outraPalavra)==0;
    }
    private void calcularVitoria(){
        vitoria = getAcertoDeLetras()==palavra.length();
        fim_jogo = vitoria;
    }
}
