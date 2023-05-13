package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//capture les pièces se situant sur de ses cases de déplacement
public class FeeriqPawn extends ChessPiece{

	public FeeriqPawn(ChessBoard board, Player p, int y, int x) {
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
		// TODO Auto-generated method stub
		return "FP";
	}

}
