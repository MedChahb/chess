package chessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import pieces.*;
import player.*;

public class ChessBoard extends JFrame implements MouseListener{
    private List<ChessPiece> WpiecesOnBoard=new ArrayList<ChessPiece>();
    private List<ChessPiece> BpiecesOnBoard=new ArrayList<ChessPiece>();
    private String gameMode;
    private Player whitePlayer, blackPlayer;
    private JPanel boardPanel;
    private ChessPiece PieceClicked;
    
    private static int turn = 0; // si 0 c'est au White à jouer, 1 Black
    
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 80; 
    
    // creation de l'échiquier
    public ChessBoard(Player Wp, Player Bp, String mode) {
    	this.gameMode = mode;
    	
    	addMouseListener(this);
    	
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
                    cellPanel.setBackground(new Color(232,215,184));
                } 
                else {
                    cellPanel.setBackground(new Color(181,136,99));
                }

                boardPanel.add(cellPanel);
            }
        }
        
        // !!!! ne pas oublier d'ajouter les Piece féérique
        
        // ----- placing Pawns (blanc puis noir)
        Player p = Wp;
        Color color = Color.WHITE;
        int PositionRow = 6;
        ChessPiece pawn;
        
        for(int i=0; i<16; i++) { 	
        	if(i==8) {p = Bp; PositionRow = 1;color = Color.BLACK;} // on place les pieces noirs 
        	
        	if ((gameMode.equalsIgnoreCase("Pieces Féériques")) && (i==3 || i==11)) pawn = new FeeriqPawn(this, p, PositionRow, i%8);
        	else pawn = new Queen(this, p, PositionRow, i%8);
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
        ChessPiece knight;
        
        for(int i=0; i<4; i++) {
        	if(i==2) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = ((i%2==0)?1 : 6);
        	if(gameMode.equalsIgnoreCase("standard")) { knight = new Knight(this, p, PositionRow, PositionColumn); }
        	else {knight = new FeeriqPrincesse(this, p, PositionRow, PositionColumn) ;}
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
        ChessPiece bishop;
        
        for(int i=0; i<4; i++) {
        	if(i==2) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = ((i%2==0)?2 : 5);
        	if (gameMode.equalsIgnoreCase("standard")) bishop = new Bishop(this, p, PositionRow, PositionColumn); 
        	else bishop = new FeeriqBishop(this, p, PositionRow, PositionColumn); 
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
        ChessPiece queen;
        
        for(int i=0; i<2; i++) {
        	if(i==1) {p = Bp; PositionRow = 0;color = Color.BLACK;} // on place les pieces noirs
        	
        	int PositionColumn = 4;
        	if(gameMode.equalsIgnoreCase("standard")) queen = new Queen(this, p, PositionRow, PositionColumn); 
        	else queen = new FeeriqSauterelle(this, p, PositionRow, PositionColumn);
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

    public Player getWhitePlayer() {return this.whitePlayer;}
	public Player getBlackPlayer() {return this.blackPlayer;}
	public String getGameMode() { return this.gameMode;}
	public List<ChessPiece> getWpiecesOnBoard(){ return this.WpiecesOnBoard;}
	public List<ChessPiece> getBpiecesOnBoard(){ return this.BpiecesOnBoard;}


	public ChessPiece PieceClicked(List<ChessPiece> listPieces, Move startMove) {
		//cherche dans la liste la piece de coord startMove
		for(ChessPiece piece : listPieces) {
			if(piece.getX() == startMove.getX() && piece.getY() == startMove.getY()) {
				return piece;
			}
		}
		return null;
	}
	
	
	// return la piece clické selon le tour de jeu.
	// null si pas de piece sur la souris
	public ChessPiece PlayerClickedPiece(Move startMove) {
		if (turn == 0) {
			return this.PieceClicked(WpiecesOnBoard, startMove);
		}
		else return this.PieceClicked(BpiecesOnBoard, startMove);
	}

		
	
	public void mouseClicked(MouseEvent e) {
		maskRange();
		Move move =  Move.mouseCoordToBoardCoord(e.getX(), e.getY());
		PieceClicked = PlayerClickedPiece(move);
		showRangePiece();
	}
	
	public void showRangePiece() {
		if(PieceClicked!= null) {
			//reinisitlisation des pieces que Piececlicked peut capturer à chaque click
			PieceClicked.emptyToCapture();
			List<Move> range = PieceClicked.PieceMoves();
			for(Move m : range) {
				JPanel cellPanel = (JPanel) boardPanel.getComponent(m.getY() * 8 + m.getX());
				cellPanel.setBackground(Color.RED);
				cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			}
		}
    }
	public void maskRange() {
		if(PieceClicked!= null) {
			PieceClicked.emptyToCapture();
			List<Move> range = PieceClicked.PieceMoves();
			for(Move m : range) {
				JPanel cellPanel = (JPanel) boardPanel.getComponent(m.getY() * 8 + m.getX());
				if ((m.getY() + m.getX()) % 2 == 0) { 
                    cellPanel.setBackground(new Color(232,215,184));
                } else {
                    cellPanel.setBackground(new Color(181,136,99));
                }
				cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
				
			}
		}
		
	}
	public void movePiece(Player player, Move startMove) {
		//
    }
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {

		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

    // Other methods for game logic, such as checking for checkmate, stalemate, etc.
}

