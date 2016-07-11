package Projeto;

public class Main {

	public int [][] tabuleiroInicial = new int [2][2];
	public Tabuleiro tabuleiroRaiz;
	public int JOGADOR = 1;
	public int CPU = 2;
	public int vitoria = 1;
	public int derrota = -1;
	public int empate = 0;
	//private int[][] combVitorias = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 },{ 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };
	
	
	public void zerarTabuleiro(){
		
		for (int i = 0; i <3;i++){
			
			for (int j = 0; j <3;j++){
				
				this.tabuleiroInicial[i][j] = 0;
				
			}
			
		}
	}
	
	
}
