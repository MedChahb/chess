package chess;

import menu.Menu;
import chessBoard.ChessBoard;
import player.Player;

public class Chess {
	public static void main(String[] args) {
		
		Menu menu = new Menu();

		// Attendre le submit de l'utilisateur
        while (!menu.isSubmitted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		

		Player Wp = new Player(menu.getWhitePlayerName(), "white");
		Player Bp = new Player(menu.getBlackPlayerName(), "black");
		System.out.print(menu.getMode());	
		ChessBoard board = new ChessBoard(Wp, Bp, menu.getMode());
		
			

	}
}
