package snake;

public class Jogador {
	
	String nome;
	int pontos;
	
	public Jogador(String n,int p){
		nome = n;
		pontos = p;
	}
	public String getNome() {
		return nome;
	}
	public int getPontos(){
		return pontos;
	}
	public String toString(){
		return pontos + " "+nome;
	}
}
