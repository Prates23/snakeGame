package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class JogoMain {
	
	//arena
	Arena arena;
	//Mapa
	Mapa m;
	
	//Objectos do tipo Frame
	LinkedList<Frame> cobra =  new LinkedList<Frame>();
	Vector<Frame> mapa = new Vector<Frame>();
	LinkedList<Frame> validpoints = new LinkedList<Frame>();
	Frame rato;
	Frame ninho;
	Frame cauda;
	Frame newHead;
	
	// Imagens JPG
	public final ImageIcon cobracolor = new ImageIcon("cobra.JPG");
	public final ImageIcon arenacolor = new ImageIcon("arena.JPG");
	public final ImageIcon ratocolor  = new ImageIcon("rato.JPG");
	public final ImageIcon mapacolor  = new ImageIcon("mapa.JPG");
	public final ImageIcon ninhocolor = new ImageIcon("ninho.JPG");
	
	//Tamanho da arena
	public static final int altura = 30;
	public static final int largura = 35;

	//Teclas de jogo
	public final int up    = KeyEvent.VK_UP;
	public final int down  = KeyEvent.VK_DOWN;
	public final int right = KeyEvent.VK_RIGHT;
	public final int left  = KeyEvent.VK_LEFT;
	public final int pause = KeyEvent.VK_P;
	
	//Outras variaveis
	boolean ate   = true;
	boolean jogar = false;
	Movimento movimento = new Movimento(0);
	Janela janela;
	boolean firsttec = true;
	
	//Ranking
	Ranking ranking;
	int minPoint;
	final String nomap = "nomapranking.txt";
	final String mapa1ranking = "mapa1ranking.txt";
	final String mapa2ranking = "mapa2ranking.txt";
	final String mapa3ranking = "mapa3ranking.txt";
	
	//Bonus ninho
	JLabel b;
	boolean bonus = false;
	int tbonus;
	int bonuspoints;
	final int tdelay = 100;
	Timer t = new Timer(tdelay,new Action());
	
	//Pontos
	JLabel p;
	int pontos;
	int nivel = 1;
	
	public JogoMain() throws IOException{	
		janela = new Janela(altura,largura,arenacolor,new MyKeyEvent(),
		                    new menuListener());
		arena = janela.getArena();
	
		p = janela.getPointsLabel();
		b= janela.getBonusLabel();
		ranking = new Ranking(nomap);
		minPoint = ranking.getMinPoint();
		start();		
	}
	//este paint é tão somente mudar a cor da cauda e da cabeça da cobra dum JPanel, dado que cada quadricula é um JPanel.
	//arenacolor é uma imagem e cobracolor também.
	public void paint(){
		arena.setframe(cauda,arenacolor);
		arena.setframe(newHead, cobracolor);
	}
	public void start(){
		p.setText("  "+0+"  ");
		b.setText("");
		Iterator<Frame> it = cobra.iterator();
		while(it.hasNext()){
			arena.setframe(it.next(), arenacolor);
		}
		if(cauda !=null)
			arena.setframe(cauda, arenacolor);
		if(rato != null)
			arena.setframe(rato, arenacolor);
		ate = true;
		if(bonus){
			arena.setframe(ninho, arenacolor);
			bonus = false;
		}
		if(mapa.size()!=0)
			for(int i=0; i<mapa.size();++i)
				arena.setframe(mapa.get(i), mapacolor);
		
		validpoints.clear();
		cobra.clear();
		getValidPoints();
		cobra.addFirst((validpoints.get((int)(Math.random()*validpoints.size()))));
		arena.setframe(cobra.get(0), cobracolor);
		movimento = new Movimento(0);
		pontos = 0;
	}
	public void mapa(String FileName) throws IOException{
		t.stop();
		jogar = false;
		for(int i=0;i<mapa.size();++i)
			arena.setframe(mapa.get(i), arenacolor);
		m = new Mapa(FileName);
		mapa = (Vector<Frame>)m.getMapa();
	}
	public void selectMove() throws IOException{
		switch(movimento.getMovement()){
		case up :move(-1,0) ;break;
		case down :move(1,0) ;break;
		case right :move(0,1) ;break;
		case left :move(0,-1) ;break;
		}
	}
	public void move(int y,int x) throws IOException{
		newHead = new Frame(cobra.get(0).gety(),cobra.get(0).getx());
		newHead.addPos(y, x);
		cauda = cobra.removeLast();
		Iterator<Frame> it = cobra.iterator();
		//verifica se a cobra vai contra o proprio corpo
		while(it.hasNext()){
			if(newHead.equals(it.next())){
				gameOver();
				return;
			}
		}
		//verifica se a cobra vai contra o mapa
		for(int j=0;j<mapa.size();++j){
			if(mapa.get(j).equals(newHead)){
				gameOver();
				return;
			}
		}
		cobra.addFirst(newHead);
		removeValidPoint(newHead);
		validpoints.add(cauda); //a posiçao posterior a' cobra passa a ser valida
		if(newHead.equals(rato)){
			cobra.add(cauda);
			ate = true;
			pontos +=nivel;
			p.setText("  "+pontos+"  ");
		}
		else if(bonus){
			if(tbonus > 50){
				bonus = false;
				arena.setframe(ninho,arenacolor);
				b.setText("");
			}else if(newHead.equals(ninho)){
				pontos+= bonuspoints;
				bonus = false;
				p.setText("  "+pontos+"  ");
				b.setText("");
			}else
				++tbonus;
		}
	}
	public void gameOver() throws IOException{
		t.stop();
		jogar = false;
		if(!ranking.cheia() || minPoint < pontos){
			String a =JOptionPane.showInputDialog(janela,"Insira o seu nome: ", "Game Over",JOptionPane.OK_OPTION);
			a = a==null || a.equals("") ? "no name":a;
			ranking.novoJogador(new Jogador(a,pontos));
			minPoint=ranking.getMinPoint();
		}else
			JOptionPane.showConfirmDialog(janela,"Game Over","Game Over",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
		start();
	}
	
	public void getValidPoints(){
		if(mapa.size()!=0){
			boolean found = false;
			for(int i=0;i<altura;++i)
				for(int j=0;j<largura;++j){
					for(int k=0;k<mapa.size();++k){
						if(mapa.get(k).getx()==j && mapa.get(k).gety()==i){
							found = true;
							break;
						}
					}
					if(!found){
						validpoints.add(new Frame(i,j));
					}
					else
						found = false;
				}
		}else{
			for(int i=0;i<altura;++i)
				for(int j=0;j<largura;++j)
					validpoints.add(new Frame(i,j));
		}
	}
	//remover da lista de pontos validos um ponto
	public void removeValidPoint(Frame rem){
		Iterator<Frame> it = validpoints.iterator();
		while(it.hasNext()){
			if(it.next().equals(rem)){
				it.remove();
				break;
			}
		}
	}
	// Acçao do timer
	private class Action implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			firsttec = true;
			if(ate){
				int idx = (int)(Math.random() * validpoints.size());
				int yy = validpoints.get(idx).gety();
				int xx = validpoints.get(idx).getx();
				rato = new Frame(yy,xx);
				removeValidPoint(rato);
				arena.setframe(rato,ratocolor);
				ate = false;
			}
			if(!bonus){
				int random = (int)(Math.random()*200);
				if(random == 0){
					ninho = (validpoints.get((int)(Math.random()*validpoints.size())));
					arena.setframe(ninho,ninhocolor);
					bonus = true;
					tbonus = 0;
					bonuspoints = (int)(Math.random()*4) + 1;
					b.setText("Bonus: "+bonuspoints);
				}
			}
			try{
			selectMove();
			}catch(IOException e){e.getMessage();}
			if(jogar)
				paint();
		}	
	}
	// KeyEvent, obtem o codigo da tecla 
	private class MyKeyEvent implements KeyListener{
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if( code == up || code == down ||code == left ||code == right ||
					                                                code == pause ){
				if(firsttec){
					movimento.setMovement(code);
					firsttec = false;
					if(!jogar){
						jogar = true;
						t.start();
					}	
					
				}
				else if( code == pause && jogar ){
					jogar=false;
					t.stop();
				}
			}
		}	
		public void keyReleased(KeyEvent e) {}
		// 
		public void keyTyped(KeyEvent e) {}	
		//
	}
	// ActionListener do MENU
	private class menuListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("Nivel 1")){
				nivel = 1;
				t.setDelay(tdelay);
			}	
			if(e.getActionCommand().equals("Nivel 2")){
				nivel = 2;
				t.setDelay(tdelay/2);
			}	
			if(e.getActionCommand().equals("Nivel 3")){
				nivel = 3;
				t.setDelay(tdelay/4);
			}
			if(e.getActionCommand().equals("New Game")){
				t.stop();
				start();
			}
			if(e.getActionCommand().equals("Sem Mapa")){
				for(int i=0;i<mapa.size();++i)
					arena.setframe(mapa.get(i), arenacolor);
				mapa.removeAllElements();
				try{
				ranking = new Ranking(nomap);
				minPoint = ranking.getMinPoint();
				gameOver();
				}catch(IOException ex){ex.getMessage();}
			}
			if(e.getActionCommand().equals("Mapa 1")){
				try{
					ranking = new Ranking(mapa1ranking);
					minPoint = ranking.getMinPoint();
					mapa("mapa1.txt");
					gameOver();
				} catch(IOException ex){};	
			}
			if(e.getActionCommand().equals("Mapa 2")){
				try{
					ranking = new Ranking(mapa2ranking);
					minPoint = ranking.getMinPoint();
					mapa("mapa2.txt");
					gameOver();
				}catch(IOException ex){};	
			}
			if(e.getActionCommand().equals("Mapa 3")){
				try{
					ranking = new Ranking(mapa3ranking);
					minPoint = ranking.getMinPoint();
					mapa("mapa3.txt");
					gameOver();
				}catch(IOException ex){};	
			}
			if(e.getActionCommand().equals("Exit")){
				System.exit(0);
			}
			if(e.getActionCommand().equals("Top 5")){
				new Top5Window(ranking.getList());
			}
		}
	}
	public static void main(String[] args) throws IOException{
		new gerarMapa("mapa1.txt");
		new JogoMain();
	}
}
