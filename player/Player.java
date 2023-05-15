package player;

import java.awt.Color;
import java.util.List;

import chessBoard.ChessBoard;
import pieces.ChessPiece;

public class Player {
    private String name_;
    private String color_; // white ou black
    private boolean inCheck = false;
    private boolean isCheckmate;
    private boolean isStalemate;
    private List<ChessPiece> piecesLeft;
    

    public Player(String name, String color) {
        this.name_ = name;
        this.color_ = color;
    }

    // Getter and setter methods for the above properties

    public List<Move> LegalMoves(ChessBoard board) {
        // Code to get the legal moves for the current turn
    	return null;
    }


    public boolean PlayerisWhite() {
    	return this.color_.equalsIgnoreCase("white");
    }
    


    public boolean isCheckmate(ChessBoard board) {
        // Code to check if the player is in checkmate
    	return false;
    }

    public boolean isStalemate(ChessBoard board) {
        // Code to check if the game is in stalemate
    	return false;
    }
    
    public void setColorPlayer(String color) { this.color_ = color;} 
    public String getPlayerName() { return this.name_;}
    
    public List<ChessPiece> getPiecesLeft(){ return this.piecesLeft;}
    public void setPiecesLeft(List<ChessPiece> pl) {
    	this.piecesLeft = pl;
    }
    
    public String toString() { return "player: " + this.color_;}
}
