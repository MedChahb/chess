package pieces;

import java.util.*;

import chessBoard.ChessBoard;
import player.*;

public abstract class ChessPiece {

	protected ChessBoard board_;
	protected Player player_;
	protected int x_,y_;
	
	private boolean isRangeShowing = false;

	
	public ChessPiece(ChessBoard board, Player p, int y, int x) {
		this.board_ = board;this.player_ = p; y_ = y; this.x_ = x;
	}
	
	//getters & setters
	public int getX() {return x_;}
	public void setX(int x) {this.x_ = x;}

	public int getY() {return y_;}
	public void setY(int y) {this.y_ = y;}

	
	public ChessBoard getBoard() { return this.board_;}
	
	public Player getPlayer() {return this.player_;}
	
	public  boolean isRangeShowing() {return isRangeShowing;}
	public  void setRangeShowing(boolean isShowing) {isRangeShowing = isShowing;}

	
	public boolean cellTakenAlly(Move move) {
		if(player_.PlayerisWhite()) {
			for(ChessPiece piece : board_.getWpiecesOnBoard()) {
				if (piece.getX() == move.getX() && piece.getY() == move.getY()) {
					return true;
				}
			}		
		}
		else { // player is Black
			for(ChessPiece piece : board_.getBpiecesOnBoard()) {
				if(piece.getX() == move.getX() && piece.getY() == move.getY()) {
					return true;
				}
			}		
		}
		return false;
	}	
	public boolean moveInBoard(Move move) {
		return 0<= move.getX() && move.getX()<=7 && 0<=move.getY() && move.getY()<=7;
	}
	
	public boolean isValidMove(Move move) {
		return moveInBoard(move) && !cellTakenAlly(move); // to change 
	}
	
	
	public abstract List<Move> PieceMoves();
	
	public abstract String toString();
	
	
}
