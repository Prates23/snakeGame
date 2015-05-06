package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Janela extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Arena arena;
	JLabel p;
	JLabel bonus;
	
	public Janela(int altura,int largura,ImageIcon ic, KeyListener MyListener,ActionListener
			menuListener){
		
		arena = new Arena(altura,largura,ic);
		add(arena.getPanel(),BorderLayout.SOUTH);
		
		MyMenuBar myMenu = new MyMenuBar(menuListener);
		add(myMenu.getMenuBar(),BorderLayout.NORTH);
		
		JPanel p1 = new JPanel();
		p = new JLabel("  0  ");
		bonus = new JLabel("");
		bonus.setForeground(Color.YELLOW);
		p1.add(p,BorderLayout.CENTER);
		p1.add(bonus);
		add(p1,BorderLayout.CENTER);
		p.setOpaque(true);
		p.setBackground(Color.RED);
		p1.setBackground(Color.black);
		
		addKeyListener(MyListener);
	//---------------	
	    setTitle("JOGO da SNAKE");
		setResizable(false);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public Arena getArena(){
		return arena;
	}
	public JLabel getPointsLabel(){
		return p;
	}
	public JLabel getBonusLabel(){
		return bonus;
	}
}
