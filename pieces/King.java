package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class King extends ChessPiece{

	public King(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	public boolean isValidMove(int endX, int endY) {
		return true;
	}

	public String toString() {
		return "K";
	}

	@Override
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Moves.add(new Move(y_+1, x_));
		Moves.add(new Move(y_-1, x_));
		Moves.add(new Move(y_-1, x_-1));
		Moves.add(new Move(y_-1, x_+1));
		Moves.add(new Move(y_+1, x_+1));
		Moves.add(new Move(y_-1, x_+1));
		Moves.add(new Move(y_, x_+1));
		Moves.add(new Move(y_, x_-1));
		return Moves;
	}

}
