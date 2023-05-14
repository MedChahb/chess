package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class Knight extends ChessPiece{

	public Knight(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}


	public String toString() {
		return "Kn";
	}

	@Override
	public List<Move> PieceMoves() {
		List<Move> Moves = new ArrayList<>();
		Move move;
		// le chevalier se deplace suivant une cercle de rayon 5. (L 1,2)
		for (int i = -2; i <= 2; i++) {
		    for (int j = -2; j <= 2; j++) {
		        if (i*i+j*j == 5) {
		        	move = new Move(x_+i, y_+j);
		    		if(isValidMove(move)) Moves.add(move);
		    		if(cellTakenEnemy(move)) toCapture.add(move);
		    		
		        }
		    }
		}
		return Moves;
	}
}

