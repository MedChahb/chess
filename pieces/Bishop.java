package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Bishop extends ChessPiece{

	public Bishop(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	public boolean isValidMove(int endX, int endY) {
		return true;
	}

	public String toString() {
		return "B";
	}

	@Override
	public List<Move> PieceMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
