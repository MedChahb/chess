package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//capture les pièces se situant sur de ses cases de déplacement
public class FeeriqPawn extends ChessPiece{

	public FeeriqPawn(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		if(player_.PlayerisWhite()) {
			move = new Move(x_, y_-1);
			if(moveInBoard(move) && !cellTakenAlly(move)) {Moves.add(move); toCapture.add(move);}
			
			if(!cellTakenAlly(move) && !cellTakenEnemy(move)) {
				move = new Move(x_, y_-2);
				if(moveInBoard(move)) {Moves.add(move); toCapture.add(move);}
			}
			// les captures
			move = new Move(x_+1, y_-1); // coin droit
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			move = new Move(x_-1, y_-1); // coin gauche
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			
		}
		else {
			move = new Move(x_, y_+1);
			if(moveInBoard(move) && !cellTakenAlly(move)) {Moves.add(move); toCapture.add(move);}
			if(!cellTakenAlly(move) && !cellTakenEnemy(move)) {
				move = new Move(x_, y_+2);
				if(moveInBoard(move)) {Moves.add(move); toCapture.add(move);}
			}
			//les caprutes
			move = new Move(x_+1, y_+1); // coin droit
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			move = new Move(x_-1, y_+1); // coin gauche
			if(moveInBoard(move) && !cellTakenAlly(move) && cellTakenEnemy(move)) {Moves.add(move); toCapture.add(move);}
			

		}
		return Moves;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FP";
	}

}
