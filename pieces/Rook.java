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
		List<Move> Moves = new ArrayList<>();
		Move move;
		for(int i=1; i<8; i++) {
			move = new Move(x_, y_-i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move) || cellTakenKingEnemy(move)) Moves.add(move);
			else if(cellTakenEnemy(move)) {
				toCapture.add(move);
				Moves.add(move);
				break;
			}
			
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_, y_+i);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move) || cellTakenKingEnemy(move)) Moves.add(move);
			else if(cellTakenEnemy(move)) {
				toCapture.add(move);
				Moves.add(move);
				break;
			}
			
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_-i, y_);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move) || cellTakenKingEnemy(move)) Moves.add(move);
			
			else if (cellTakenEnemy(move)) {
				toCapture.add(move);
				Moves.add(move);
				break;
			}
			
		}
		for(int i=1; i<8; i++) {
			move = new Move(x_+i, y_);
			if(cellTakenAlly(move)) break;
			if(moveInBoard(move) || cellTakenKingEnemy(move)) Moves.add(move);
			else if (cellTakenEnemy(move)) {
				toCapture.add(move);
				Moves.add(move);
				break;
			}
			
		}
		return Moves;
	}

}
