package pieces;

import java.util.*;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Pawn extends ChessPiece{
	private boolean moved = false;
	
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
			if(moveInBoard(move) && !cellTakenAlly(move) && !cellTakenEnemy(move)) Moves.add(move);
			// les captures
			move = new Move(x_+1, y_-1); // coin droit
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			move = new Move(x_-1, y_-1); // coin gauche
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			
			if(!moved) {
				Move case1 = new Move(x_, y_-1);
				move = new Move(x_, y_-2);
				if(moveInBoard(move) && !cellTakenAlly(move) && !cellTakenEnemy(move) && !cellTakenAlly(case1) && !cellTakenEnemy(case1) ) Moves.add(move);
			}
		}
		else {
			move = new Move(x_, y_+1);
			if(moveInBoard(move) && !cellTakenAlly(move) && !cellTakenEnemy(move)) Moves.add(move);
			//les caprutes
			move = new Move(x_+1, y_+1); // coin droit
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			move = new Move(x_-1, y_+1); // coin gauche
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			
			if(!moved) {
				Move case1 = new Move(x_, y_+1);
				move = new Move(x_, y_+2);
				if(moveInBoard(move) && !cellTakenAlly(move) && !cellTakenEnemy(move) && !cellTakenAlly(case1) && !cellTakenEnemy(case1) ) Moves.add(move);
			}
		}
		return Moves;
	}
	
	public List<Move> PawnDefCell(){
		List<Move> cells = new ArrayList<>();
		if(player_.PlayerisWhite()) {
			Move move = new Move(x_+1, y_-1); //coin droit
			if(moveInBoard(move)) cells.add(move);
			move = new Move(x_-1, y_-1); //coin gauche
			if(moveInBoard(move)) cells.add(move);
		}
		else {
			Move move = new Move(x_+1, y_+1); //coin droit
			if(moveInBoard(move)) cells.add(move);
			move = new Move(x_-1, y_+1); //coin gauche
			if(moveInBoard(move)) cells.add(move);
		}
		return cells;
	}
	
	public void hasMoved() { this.moved = true;}
	
	public String toString() {
		return "P";
	}
}
