package player;

public class Move {
	private int y_,x_;
	
	public Move(int x, int y) {
		this.x_ = x;
		this.y_ = y;
	}

	
	public static Move mouseCoordToBoardCoord(int x, int y) {
		x -= 8; y-=30; // afin de rendre le repère à (0,0) comme point d'origine non pas (8,30) 
		return new Move((int)Math.floor(x/80),(int)Math.floor(y/80));
	}
	
	public int getY() {
		return y_;
	}
	public int getX() {
		return x_;
	}
	public void setX(int x) {
		this.x_ = x;
	}
	public void setY(int y) {
		this.y_ = y;
	}
	
	
	public String toString() { return "X : "+x_+" Y: "+y_;}
		
}
