/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodaforca;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jogodaforca.modelo.Palavra;

/**
 *
 * @author Thiago
 */
public class Partida {
    private String palavra;
    private String tema;
    private int erros;
    private int max_erros;
    private char letras_erradas[];
    private boolean posicoes_visiveis[];
    private boolean fim_jogo = false;
    private boolean vitoria = false;
    private boolean chutePalavra = false;
    
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
            calcularVitoria(false);
        }
        return l;
    }

    public int chutarPalavra(String str){
    	
        int i, l = 0;
        boolean acertou = false;
        chutePalavra = true;
        
        if(palavra.equalsIgnoreCase(str)){
        	for(i=0;i<=palavra.length()-1;i++){
        		if(!posicoes_visiveis[i]){
        			posicoes_visiveis[i] = true;
        			l++;
        		}
			}
        	
        	acertou = true;
    	}
        
        if(!acertou){
        	for(i=0;i<=str.length()-1;i++){
        		if(!posicoes_visiveis[i])
        			verificaLetraErrada(str.charAt(i));
        		
        		if(fim_jogo) break;
        	}
        }else{
            calcularVitoria(true);
        }
        
        chutePalavra = false;
        return l;
    }
    
    public Partida(ArrayList<Palavra> palavras, int total_erros) {
        int i;
        i = (int) (Math.random() * palavras.size());
        this.palavra = palavras.get(i).getPalavra();
        this.tema = palavras.get(i).getTema();
        this.max_erros = total_erros;
        this.posicoes_visiveis = new boolean[palavra.length()];
        this.letras_erradas = new char[total_erros];
        this.erros = 0;
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
        String str = "",sep = "";
        int i;
        for(i=0;i<erros;i++){
            if(i>0) sep = " - ";
            str = str + sep + letras_erradas[i];
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
        if(!chutePalavra){
	        for(i=0;i<erros;i++){
	            if(letras_erradas[i]==c) v = true;
	        }
        }
        
        if(v){
            JOptionPane.showMessageDialog(null, "A letra '"+c+"' j� foi chutada!","Chute inv�lido" , JOptionPane.INFORMATION_MESSAGE);
        }else{
            erros++;
            letras_erradas[erros-1] = c;
            if(erros==max_erros)fim_jogo= true;
        }
    }

    public boolean comparaPalavra(String outraPalavra){
        return this.palavra.compareToIgnoreCase(outraPalavra)==0;
    }
   
    private void calcularVitoria(boolean isPalavra){
    	if(isPalavra)
    		vitoria = getAcertoDeLetras()==palavra.length();
    	else
    		vitoria = getAcertoDeLetras()==palavra.replace(" ", "").length();
    	
        fim_jogo = vitoria;
    }
}
