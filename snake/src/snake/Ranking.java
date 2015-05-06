package snake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Ranking {

	Vector<Jogador> lista;
	BufferedReader br;
	PrintWriter pw;
	int maxclass = 5;
	String filename;
	
	public Ranking(String listname ) throws IOException{
		filename = listname;
		lista = new Vector<Jogador>();
		br = new BufferedReader(new FileReader(listname));
		String st;
		Scanner sc;
		int pnt;
		while((st=br.readLine())!=null){
			sc = new Scanner(st);
			if(sc.hasNextInt())
				pnt = sc.nextInt();
			else
				break;
			st="";
			while(sc.hasNext())
				st+=sc.next()+" ";
			lista.add(new Jogador(st,pnt));
		}
	}
	public Vector<Jogador> getList(){
		return lista;
	}
	
	public int getMinPoint(){
		if(lista.size()==0)
			return 0;
		else
			return lista.get(lista.size()-1).getPontos();
	}
	public boolean cheia(){
		if(lista.size() == maxclass)
			return true;
		return false;
	}
	public void novoJogador(Jogador j) throws IOException{
		if(lista.size() == maxclass){
			lista.remove(maxclass-1);
			lista.add(j);
		}
		else
			lista.add(j);
		Jogador aux;
		for(int i=lista.size()-1; i>0 ; --i){
			if(lista.get(i).getPontos() > lista.get(i-1).getPontos()){
				aux = lista.get(i);
				lista.set(i, lista.get(i-1));
				lista.set(i-1,aux);
			}
			else 
				break;
		}
		pw = new PrintWriter(filename);
		for(int i=0;i<lista.size();++i)
			pw.println(lista.get(i));
		pw.flush();
		pw.close();
	}
	
}
