package Maze;

public class PairInt {
	
	// Data Fields
	private int x;
	private int y;
	
	//Constructors
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Class Methods
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		if(!(p instanceof PairInt)){
			return false;
		} else {
			PairInt P = (PairInt)p;
			return P.x == this.x && P.y ==this.y;
		}
	}
	
	public String toString() {
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
	
	public PairInt copy() {
		PairInt result = new PairInt(x,y);
		return result;
	}
	
}
