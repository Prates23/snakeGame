package snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Mapa {
	
	Vector<Frame> mapa = new Vector<Frame>();
	
	public Mapa(String FileName) throws FileNotFoundException,IOException{
		FileReader fl = new FileReader(FileName);
		BufferedReader br = new BufferedReader(fl);
		String a = "";
		Scanner sc = new Scanner(a);
		int x;
		int y;
		while((a=br.readLine())!=null){
			sc = new Scanner(a);
			if(sc.hasNextInt()){
				x=sc.nextInt();
				y=sc.nextInt();
				mapa.add(new Frame(y,x));
			}	
		}
	}
	public Vector<Frame> getMapa(){
		return mapa;
	}
}
