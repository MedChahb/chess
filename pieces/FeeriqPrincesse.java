package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//combine les mouvements d'un cavalier et d'un fou.
public class FeeriqPrincesse extends ChessPiece{

	public FeeriqPrincesse(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
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
		return "FPr";
	}

}
