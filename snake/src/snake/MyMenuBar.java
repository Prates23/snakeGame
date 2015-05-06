package snake;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar{
	
	JMenuBar menuBar;
	
	public MyMenuBar(ActionListener menuListener ){
		menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenu mapa = new JMenu("Mapa");
		JMenu nivel = new JMenu("Nivel");
		JMenuItem nomap = new JMenuItem("Sem Mapa");
		JMenuItem mapa1 = new JMenuItem("Mapa 1");
		JMenuItem mapa2 = new JMenuItem("Mapa 2");
		JMenuItem mapa3 = new JMenuItem("Mapa 3");
		JMenuItem nivel1 = new JMenuItem("Nivel 1");
		JMenuItem nivel2 = new JMenuItem("Nivel 2");
		JMenuItem nivel3 = new JMenuItem("Nivel 3");
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenuItem top5 = new JMenuItem("Top 5");
		
		mapa.add(nomap);
		mapa.add(mapa1);	nivel.add(nivel1);
		mapa.add(mapa2);	nivel.add(nivel2);
		mapa.add(mapa3);	nivel.add(nivel3);
		
		options.add(newGame);
		options.add(mapa);
		options.add(nivel);
		options.addSeparator();
		options.add(exit);
		
		newGame.addActionListener(menuListener);
		
		nivel1.addActionListener(menuListener);
		nivel2.addActionListener(menuListener);
		nivel3.addActionListener(menuListener);
		
		nomap.addActionListener(menuListener);
		mapa1.addActionListener(menuListener);
		mapa2.addActionListener(menuListener);
		mapa3.addActionListener(menuListener);
		
		top5.addActionListener(menuListener);
		
		exit.addActionListener(menuListener);
		
		menuBar.add(options);
		menuBar.add(top5);
	}
	public JMenuBar getMenuBar(){
		return menuBar;
	}
}
