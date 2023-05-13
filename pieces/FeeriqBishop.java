package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//un fou de porté limité
public class FeeriqBishop extends ChessPiece{

	public FeeriqBishop(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(int endX, int endY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Move> PieceMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Fbi";
	}

}
