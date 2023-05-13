package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//un fou de porté limité en 3cases
public class FeeriqBishop extends ChessPiece{

	public FeeriqBishop(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
		// TODO Auto-generated constructor stub
	}

	
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		for(int i=1; i<5; i++) {
			move = new Move(x_+i, y_-i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<5; i++) {
			move = new Move(x_-i, y_+i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<5; i++) {
			move = new Move(x_+i, y_+i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		for(int i=1; i<5; i++) {
			move = new Move(x_-i, y_-i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move)) Moves.add(move);
		}
		return Moves;
	}

	@Override
	public String toString() {
		return "Fbi";
	}

}
