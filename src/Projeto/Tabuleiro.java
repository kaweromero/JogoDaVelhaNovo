package Projeto;

import java.util.ArrayList;

public class Tabuleiro {

	//private String[] velha = {"?","?","?","?","?","?","?","?","?"};
	int [][] tabuleiro = new int [2][2];
	public String tipo = "max";
	public int custo = 0;
    public ArrayList<Tabuleiro> filhos = new ArrayList<Tabuleiro>();
    
	
	public Tabuleiro(int [][] tabuleiro, String tipo){
		
		copiaTabu(this.tabuleiro, tabuleiro);
		//this.tabuleiro = tabuleiro;
		
		
	}


	public int[][] getTabuleiro() {
		return tabuleiro;
	}


	public void setTabuleiro(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getCusto() {
		return custo;
	}


	public void setCusto(int custo) {
		this.custo = custo;
	}


	public ArrayList<Tabuleiro> getFilhos() {
		return filhos;
	}


	public void setFilhos(ArrayList<Tabuleiro> filhos) {
		this.filhos = filhos;
	}
	
	public void gerarArvore(){
		
    	// 'O' venceu..
        if (testeVitoria(getTabuleiro(),1)){ 
        	setCusto(-1);
        // 'X' venceu..
        } else if (testeVitoria(getTabuleiro(),2)){ 
        	setCusto(1);
        // Ainda sem um vencedor.
        	
        } else { 
        	int [][] tabuleiroAux = null;
        	copiaTabu(tabuleiroAux,this.tabuleiro);
        	
        	int [][] novoTabu = tabuleiroAux;
        	
            Tabuleiro filho = null;
            // Gera os próximos filhos.
            
        	for (int i=0; i<2 ; i++){
        		
        		for(int j = 0; j < 3; j++){
        			
        		// Encontrou uma posição vazia, inclui para gerar um novo filho.
	        	if (novoTabu[i][j] == 0){ 
	        		//- Usuário.
	        		if (getTipo().equals("max")){
	        			novoTabu[i][j] = 1;
	        			filho = new Tabuleiro(novoTabu, "min");
	        		//- CPU.
	        		} else {
	        			novoTabu[i][j] = 2;
	        			filho = new Tabuleiro(novoTabu, "max");
	        		}
	                filhos.add(filho);
	                filho.gerarArvore();
	        		}
        		}	
	        	
        		copiaTabu(novoTabu, tabuleiro);
        	//	novoTabu = velha.clone();
	        }
        }
    }
	
	public boolean testeVitoria(int [][] tabuleiro, int jogador){
		
		if(tabuleiro[0][0] == jogador && tabuleiro[0][1] ==  jogador && tabuleiro[0][2] ==  jogador){
			return true;
		}
		
		if(tabuleiro[1][0] ==  jogador && tabuleiro[1][1] ==  jogador && tabuleiro[1][2] ==  jogador){
			return true;
		}
		
		if(tabuleiro[2][0] ==  jogador && tabuleiro[2][1] ==  jogador && tabuleiro[2][2] ==  jogador){
			return true;
		}
		
		if(tabuleiro[0][0] == jogador && tabuleiro[1][0] ==  jogador && tabuleiro[2][0] ==  jogador){
			return true;
		}
		
		if(tabuleiro[0][1] ==  jogador && tabuleiro[1][1] ==  jogador && tabuleiro[2][1] ==  jogador){
			return true;
		}
		
		if(tabuleiro[0][2] ==  jogador && tabuleiro[1][2] ==  jogador && tabuleiro[2][2] ==  jogador){
			
			return true;
		}
		
		if(tabuleiro[0][0] == jogador && tabuleiro[1][1] ==  jogador && tabuleiro[2][2] ==  jogador){
			return true;
		}
		
		if(tabuleiro[2][0] ==  jogador && tabuleiro[1][1] ==  jogador && tabuleiro[0][2] ==  jogador){
			return true;
		}
		
		
		return false;
	}
	
	public void copiaTabu(int [][] tabuAlvo,int [][] tabuPraCopiar){
		
		
		for(int i = 0; i <3;i++){
			
			for(int j = 0; j <3;j++){
				
				tabuAlvo [i][j] = tabuPraCopiar[i][j];
				
			}
			
		}
		
		
	}
}