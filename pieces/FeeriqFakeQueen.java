package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;
//Queen Saute par-dessus les pièces.
public class FeeriqFakeQueen extends ChessPiece{

	public FeeriqFakeQueen(ChessBoard board, Player p, int y, int x) {
		super(board, p, y, x);
	}



	@Override
	public List<Move> PieceMoves() {
		List<Move> range = new ArrayList<Move>();
		
		for(int i=-2; i<=2; i++) {
			for (int j=-2; j<=2; j++) {
				if(i!=0 || j!=0) {
					Move move = new Move(x_+i, y_+j);
					if(cellTakenKingEnemy(move)) {range.add(move);}
					else {
						if(moveInBoard(move) && !cellTakenAlly(move)) range.add(move);
						if(cellTakenEnemy(move)) {
							toCapture.add(move);
							range.add(move);
						}
					}
				}
			}
		}
		
		return range;
	}

	@Override
	public String toString() {
		return "FQ";
	}

}
