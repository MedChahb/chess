package pieces;

import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

//Queen Saute par-dessus les pièces.
public class FeeriqSauterelle extends ChessPiece{

	public FeeriqSauterelle(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}



	@Override
	public List<Move> PieceMoves() {
		
		return null;
	}

	@Override
	public String toString() {
		return "FQs";
	}

}
