package pieces;

import java.util.*;

import chessBoard.ChessBoard;
import player.*;

public abstract class ChessPiece {

	protected ChessBoard board_;
	protected Player player_;
	protected int x_,y_;
	
	
	public ChessPiece(ChessBoard board, Player p, int y, int x) {
		this.board_ = board;this.player_ = p; y_ = y; this.x_ = x;
	}
	
	//getters & setters
	public int getX_() {return x_;}
	public void setX_(int x) {this.x_ = x;}

	public int getY_() {return y_;}
	public void setY_(int y) {this.y_ = y;}

	
	public ChessBoard getBoard() { return this.board_;}
	
	public Player getPlayer() {return this.player_;}

	public Player getPlayer_() {return player_;}

	
	public abstract boolean isValidMove(int endX, int endY);
	public abstract List<Move> PieceMoves();
	
	public abstract String toString();
	
	
}
