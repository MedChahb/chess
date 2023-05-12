package pieces;

import java.util.*;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Pawn extends ChessPiece{

	public Pawn(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	@Override
	public boolean isValidMove(int endX, int endY) {
		return true;
	}



	@Override
	// a voir les move non valides
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		if(player_.PlayerisWhite()) {
			Moves.add(new Move(y_-1, x_));
			Moves.add(new Move(y_-2, x_));
		}
		else {
			Moves.add(new Move(y_+1, x_));
			Moves.add(new Move(y_+2, x_));
		}
		return Moves;
	}
	
	
	public String toString() {
		return "P";
	}
}
