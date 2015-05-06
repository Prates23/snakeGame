package snake;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Arena {

	public JPanel panel;
	JLabel[][] label;
	
	public Arena(int altura, int largura,ImageIcon ic){
		
		GridLayout grid = new GridLayout(altura,largura);//,1,1);
		panel = new JPanel();
		panel.setLayout(grid);
		label= new JLabel[altura][largura];
		for(int i=0;i< altura;++i)
			for(int j=0;j<largura;++j){
				panel.add(label[i][j]=new JLabel());
				label[i][j].setIcon(ic);
			}		
	}
	
	public JPanel getPanel(){ 
		return panel;
	}
	
	public void setframe(Frame f,ImageIcon i){
		label[f.gety()][f.getx()].setIcon(i);
	}
}
