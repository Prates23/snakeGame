package snake;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top5Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Top5Window(Vector<Jogador> v ){
		super("Top 5");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		GridLayout gl = new GridLayout(5,1);
		p2.setLayout(gl);
		
		JLabel titulo = new JLabel("   Os Melhores:   ");
		p1.add(titulo);
		
		getList(v,p2);
		
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	
	public void getList(Vector<Jogador> v,JPanel p2){
		int i = 0;
		for(;i<v.size();++i){
			p2.add(new JLabel(v.get(i).toString()));
		}
		for( ;i<5;++i){
			p2.add(new JLabel("          -     "));
		}
	}
}
