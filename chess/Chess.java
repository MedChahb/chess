package chess;

import chessBoard.ChessBoard;
import player.Player;

public class Chess {
	public static void main(String[] args) {
		Player Wp = new Player("test", "white");
		Player Bp = new Player("test", "black");
		ChessBoard board = new ChessBoard(Wp, Bp);
	}
}
