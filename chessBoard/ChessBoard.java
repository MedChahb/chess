package chessBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import pieces.*;
import player.Player;

public class ChessBoard extends JFrame{
    private List<ChessPiece> WpiecesOnBoard=new ArrayList<ChessPiece>();
    private List<ChessPiece> BpiecesOnBoard=new ArrayList<ChessPiece>();  
    private Player whitePlayer, blackPlayer;
    private JPanel boardPanel;
    
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 80;
    
    // creation de l'échiquier
    public ChessBoard(Player Wp, Player Bp) {
    	
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardPanel.setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE));

        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                setResizable(false); // Make the window unsizable

                // Alternate des couleurs (j'ai pris les couleurs de lichess.org)
                if ((row + col) % 2 == 0) { // White si row et col ont la mm parité, sinon Black
                    cellPanel.setBackground(new Color(240,217,181));
                } else {
                    cellPanel.setBackground(new Color(181,136,99));
                }

                boardPanel.add(cellPanel);
            }
        }
        
        // ----- placing Pawns (blanc puis noir)
        Player p = Wp;
        Color color = Color.WHITE;
        int PositionRow = 6;
        for(int i=0; i<16; i++) { 	
        	if(i==8) {p = Bp; PositionRow = 1;color = Color.BLACK;} // on place les pieces noirs 
        	
	        ChessPiece pawn = new Pawn(this, p, PositionRow, i%8);
	        if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(pawn);} else {this.BpiecesOnBoard.add(pawn);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + i%8);
	
		    JLabel pawnLabel = new JLabel(pawn.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    cellPanel.add(pawnLabel);
        }
        
        // ----- placing Rooks (blanc puis noir)
        p = Wp;
        color = Color.WHITE;
        PositionRow = 7;
        
        for(int i=0; i<4; i++) {
        	if(i==2) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = ((i%2==0)?0 : 7);
        	ChessPiece rook = new Rook(this, p, PositionRow, PositionColumn); 
        	if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(rook);} else {this.BpiecesOnBoard.add(rook);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + PositionColumn);
	
		    JLabel pawnLabel = new JLabel(rook.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    cellPanel.add(pawnLabel);
        }
        
     // ----- placing Knights
        p = Wp;
        color = Color.WHITE;
        PositionRow = 7;
        
        for(int i=0; i<4; i++) {
        	if(i==2) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = ((i%2==0)?1 : 6);
        	ChessPiece knight = new Knight(this, p, PositionRow, PositionColumn); 
        	if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(knight);} else {this.BpiecesOnBoard.add(knight);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + PositionColumn);
	
		    JLabel pawnLabel = new JLabel(knight.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    
		    cellPanel.add(pawnLabel);
        }
        
        // ----- placing Bishops (fou)
        p = Wp;
        color = Color.WHITE;
        PositionRow = 7;
        
        for(int i=0; i<4; i++) {
        	if(i==2) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = ((i%2==0)?2 : 5);
        	ChessPiece bishop = new Bishop(this, p, PositionRow, PositionColumn); 
        	if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(bishop);} else {this.BpiecesOnBoard.add(bishop);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + PositionColumn);
	
		    JLabel pawnLabel = new JLabel(bishop.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    cellPanel.add(pawnLabel);
        }
        
     // ----- placing Kings
        p = Wp;
        color = Color.WHITE;
        PositionRow = 7;
        
        for(int i=0; i<2; i++) {
        	if(i==1) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = 3;
        	ChessPiece king = new King(this, p, PositionRow, PositionColumn); 
        	if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(king);} else {this.BpiecesOnBoard.add(king);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + PositionColumn);
	
		    JLabel pawnLabel = new JLabel(king.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    cellPanel.add(pawnLabel);
        }
        
        // ----- placing Queens
        p = Wp;
        color = Color.WHITE;
        PositionRow = 7;
        
        for(int i=0; i<2; i++) {
        	if(i==1) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = 4;
        	ChessPiece queen = new Queen(this, p, PositionRow, PositionColumn); 
        	if(p.PlayerisWhite()) {this.WpiecesOnBoard.add(queen);} else {this.BpiecesOnBoard.add(queen);}
		    JPanel cellPanel = (JPanel) boardPanel.getComponent(PositionRow * BOARD_SIZE + PositionColumn);
	
		    JLabel pawnLabel = new JLabel(queen.toString(), SwingConstants.CENTER);
		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		    pawnLabel.setForeground(color);
		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		    cellPanel.add(pawnLabel);
        }
        
        

        add(boardPanel);
        pack();
        setLocationRelativeTo(null); // afficher la fenetre en centre de l'ecran
        setVisible(true);
        
        Wp.setPiecesLeft(WpiecesOnBoard);
        Bp.setPiecesLeft(BpiecesOnBoard);
        this.whitePlayer = Wp; this.blackPlayer = Bp;
        
        
    }

    public Player getWhitePlayer() {return whitePlayer;}
	public Player getBlackPlayer() {return blackPlayer;}


	public void movePiece(Player player, int startX, int startY, int endX, int endY) {
        // Check if the move is valid and update the board
    }

    // Other methods for game logic, such as checking for checkmate, stalemate, etc.
}

