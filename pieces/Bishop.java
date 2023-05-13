package pieces;

import java.util.ArrayList;
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

	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		for(int i=1; i<8; i++) {
			move = new Move(x_+i, y_-i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_-i, y_+i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_+i, y_+i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_-i, y_-i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		return Moves;
	}

}
