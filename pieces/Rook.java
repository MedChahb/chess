package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Rook extends ChessPiece{

	public Rook(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	
	public String toString() {
		return "R";
	}

	public List<Move> PieceMoves() {
		List<Move> moves = new ArrayList<>();
		Move move;
		for(int i=-7; i<8; i++) {
			if(i!=0) {
			move = new Move(x_, y_+i);
			if(isValidMove(move)) moves.add(move);
			move = new Move(x_+i, y_);
			if(isValidMove(move)) moves.add(move);
			}
		}
		return moves;
	}

}
