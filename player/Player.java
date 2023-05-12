package player;

import java.awt.Color;
import java.util.List;

import chessBoard.ChessBoard;
import pieces.ChessPiece;

public class Player {
    private String name_;
    private String color_; // white ou black
    private boolean isCheck;
    private boolean isCheckmate;
    private boolean isStalemate;
    private List<ChessPiece> piecesLeft;
    
    private static int turn = 0; // si 0 c'est au White à jouer, 1 Black

    public Player(String name, String color) {
        this.name_ = name;
        this.color_ = color;
    }

    // Getter and setter methods for the above properties

    public List<Move> LegalMoves(ChessBoard board) {
        // Code to get the legal moves for the current turn
    	return null;
    }

    public void makeMove(ChessBoard board, Move move) {
        // Code to make a move on the board
    }


    public boolean PlayerisWhite() {
    	return this.color_.equalsIgnoreCase("white");
    }
    
    public boolean isInCheck(ChessBoard board) {
        // Code to check if the player is in check
    	return false;
    }

    public boolean isCheckmate(ChessBoard board) {
        // Code to check if the player is in checkmate
    	return false;
    }

    public boolean isStalemate(ChessBoard board) {
        // Code to check if the game is in stalemate
    	return false;
    }
    
    public String getPlayerName() { return this.name_;}
    
    public List<ChessPiece> getPiecesLest(){ return this.piecesLeft;}
    public void setPiecesLeft(List<ChessPiece> pl) {
    	this.piecesLeft = pl;
    }
    
    public String toString() { return "player: " + this.color_;}
}
