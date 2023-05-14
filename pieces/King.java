package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class King extends ChessPiece{
	private boolean inCheck = false;

	public King(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	public String toString() {
		return "K";
	}

	@Override
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		for(int i=-1; i<2; i++) {
			for (int j=-1; j<2; j++) {
				if(i!=0 || j!=0) {
					move = new Move(x_+i, y_+j);
					if(cellTakenEnemy(move)) toCapture.add(move);
					List<Move> defCell = (player_.PlayerisWhite())? board_.getBlackDefCell() : board_.getWhiteDefCell();
					if(isValidMove(move) && !move.moveInRange(defCell) && true /* not move in check cell*/ ) Moves.add(move);
				}
			}
		}
		return Moves;
	}
	//préc endMove in King.PieceMove()

    public boolean isInCheck() {
    	return this.inCheck;
    }
    public void Check(boolean inCheck) {
    	this.inCheck = inCheck;
    }
	
	// true if king is in danger in his position (color?)
    public boolean kingOnCapture() {
    	List<ChessPiece> enemyPieces = (player_.PlayerisWhite())? board_.getBpiecesOnBoard() : board_.getWpiecesOnBoard();
    	for(ChessPiece enemy : enemyPieces) {
    		for(Move range : enemy.PieceMoves()) {
    			if(x_ == range.getX() && y_ == range.getY()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }    
    
    
    public boolean isSafe(Move move, King king) {
    	return false; //dumb return 
    }

    

}
