package pieces;

import java.util.List;
import java.util.stream.Stream;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//combine les mouvements d'un cavalier et d'un fou.
public class FeeriqPrincesse extends ChessPiece{

	public FeeriqPrincesse(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}


	@Override
	public List<Move> PieceMoves() {
		ChessPiece b = new Bishop(board_, player_, y_, x_);
		ChessPiece k = new Knight(board_, player_, y_, x_);
		return Stream.concat(b.PieceMoves().stream(), k.PieceMoves().stream()).toList();
	}

	@Override
	public String toString() {
		return "FPr";
	}

}
