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
		
		List<Move> bMoves = b.PieceMoves();
		List<Move> kMoves = k.PieceMoves();
		
		this.toCapture = Stream.concat(b.getToCapture().stream(), k.getToCapture().stream()).toList();
		
		return Stream.concat(bMoves.stream(), kMoves.stream()).toList();
	}

	@Override
	public String toString() {
		return "FPr";
	}

}
