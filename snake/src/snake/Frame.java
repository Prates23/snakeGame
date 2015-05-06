package snake;

public class Frame {
	
	int x;
	int y;
	
	public Frame(int y, int x){
		this.x=x;
		this.y=y;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public void addPos(int y, int x){
		if(this.x+x==JogoMain.largura)
			this.x=0;
		else if(this.x+x==-1)
			this.x=JogoMain.largura-1;
		else
			this.x+=x;
		if(this.y+y==JogoMain.altura)
			this.y=0;
		else if(this.y+y==-1)
			this.y=JogoMain.altura-1;
		else
			this.y+=y;
	}
	
	public void setNewPos(int y,int x){
		this.x = x;
		this.y = y;
	}
	public boolean equals(Frame f) {
		return (f.getx() == x && f.gety() == y);
	}
}
