package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Queen extends ChessPiece{

	public Queen(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	public boolean isValidMove(int endX, int endY) {
		return true;
	}

	public String toString() {
		return "Q";
	}

	@Override
	public List<Move> PieceMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
