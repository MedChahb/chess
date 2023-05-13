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

	public String toString() {
		return "K";
	}

	@Override
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		for(int i=-1; i<2; i++) {
			for (int j=-1; j<2; j++) {
				if(i!=0 || j!=0) {
					move = new Move(x_+i, y_+j);
					if(isValidMove(move)) Moves.add(move);
				}
			}
		}
		return Moves;
	}

}
