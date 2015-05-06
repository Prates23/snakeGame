package snake;

public class Movimento {
	int movimento; //u,d,l,r
	
	public Movimento(int move) {
		movimento = move;
	}
	public int getMovement(){
		return movimento;
	}
	public void setMovement(int move){
		int dif = movimento - move;
		if(dif != 2 && dif != -2)
			movimento = move;
	}

}
