package pieces;

import java.util.*;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Pawn extends ChessPiece{

	public Pawn(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}

	
	@Override
	// à ajouter les pieces capturables du piont
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		if(player_.PlayerisWhite()) {
			move = new Move(x_, y_-1);
			if(moveInBoard(move)) Moves.add(move);
			move = new Move(x_, y_-2);
			if(moveInBoard(move)) Moves.add(move);

		}
		else {
			move = new Move(x_, y_+1);
			if(moveInBoard(move)) Moves.add(move);
			move = new Move(x_, y_+2);
			if(moveInBoard(move)) Moves.add(move);
		}
		return Moves;
	}
	
	
	public String toString() {
		return "P";
	}
}
