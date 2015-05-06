package snake;

import java.io.IOException;
import java.io.PrintWriter;

public class gerarMapa {
	
	PrintWriter pw;
	
	public gerarMapa(String FileName) throws IOException{
		
		pw = new PrintWriter(FileName);
		
		//switch(FileName.charAt(5)-'0'){
		//case 1: mapa1();break;
		//case 2: mapa2();break;
		//case 3: mapa3();break;
		//}
		mapa1();
		pw.close();
		
	}
	
	public void mapa1(){
		
		for(int i=0;i<JogoMain.altura;++i)
			for(int j=0;j<JogoMain.largura;++j){
				if(i==0 || i==JogoMain.altura-1){
					pw.println(j+" "+i);
				}
				else if( (i<13 || i>16) && (j == 0 || j == JogoMain.largura-1) ){
					pw.println(j+" "+i);
				}
			}
	}

}
