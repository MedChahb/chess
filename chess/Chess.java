package chess;

import menu.Menu;
import menu.Stopgame;
import chessBoard.ChessBoard;
import player.Player;

public class Chess {
	

	
	public static void main(String[] args) {
		
		
		Menu menu = new Menu();

		// Attendre le submit de l'utilisateur
        while (!menu.isSubmitted()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		

		Player Wp = new Player(menu.getWhitePlayerName(), "white");
		Player Bp = new Player(menu.getBlackPlayerName(), "black");
		ChessBoard board = new ChessBoard(Wp, Bp, menu.getMode());
		Stopgame stop = new Stopgame();
		//gameEnded end = new gameEnded("LOOl");

	}
}
