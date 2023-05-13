package pieces;

import java.util.List;
import java.util.stream.Stream;

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
		ChessPiece b = new Bishop(board_, player_, y_, x_);
		ChessPiece r = new Rook(board_, player_, y_, x_);
		
		return Stream.concat(b.PieceMoves().stream(), r.PieceMoves().stream()).toList();
	}

}
