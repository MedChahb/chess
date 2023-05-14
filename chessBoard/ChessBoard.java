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
    private ChessPiece PieceSelected;
    
	private ChessPiece[][] PiecesOnBoard = new ChessPiece[8][8];
    private static int turn = 0; // si 0 c'est au White à jouer, 1 Black
    
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 80; 
    
    
    
	public static void affiche2d(ChessPiece[][] list) {
		System.out.print("\n");
		for(ChessPiece[] inList : list) {
			for(ChessPiece p : inList) {
				if(p != null) {
					if(p.toString().length() == 1)
						System.out.print(" " +p + "   |");
					else
						System.out.print(" " +p + "  |");
				}
				else System.out.print(p + " |");
			}
			System.out.print("\n------------------------------------------------");
			System.out.print("\n");
		}
	}
    
    // creation de l'échiquier
    public ChessBoard(Player Wp, Player Bp, String mode) { 
    	
    	this.gameMode = mode;
    	
    	addMouseListener(this);
    	
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardPanel.setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE));

        
        // remplir le tableau 2D d'ici
        Color whiteCell = new Color(232,215,184);
        Color blackCell = new Color(181,136,99);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                setResizable(false); // Make the window unsizable

                // Alternate des couleurs (on a pris les couleurs de lichess.org)
                if ((row + col) % 2 == 0) { // White si row et col ont la mm parité, sinon Black
                    cellPanel.setBackground(whiteCell);
                } 
                else {
                    cellPanel.setBackground(blackCell);
                }
                boardPanel.add(cellPanel);
                
                // !!!! ne pas oublier d'ajouter les Piece féérique
                
                // ajout des Pawns
                if(row==1 || row==6) 							   PiecesOnBoard[row][col] = new Pawn	(this, (row==1)?Bp : Wp, row, col);
                //ajout des Knights
                if((row==0 || row == 7) && (col == 1 || col == 6)) PiecesOnBoard[row][col] = new Knight	(this, (row==0)?Bp : Wp, row, col);
                //ajout des Rooks
                if((row==0 || row == 7) && (col == 0 || col == 7)) PiecesOnBoard[row][col] = new Rook	(this, (row==0)?Bp : Wp, row, col);
                //ajout des Bishops
                if((row==0 || row == 7) && (col == 2 || col == 5)) PiecesOnBoard[row][col] = new Bishop	(this, (row==0)?Bp : Wp, row, col);
                //ajout des Queens
                if((row==0 || row == 7) && (col == 3)) 			   PiecesOnBoard[row][col] = new Queen	(this, (row==0)?Bp : Wp, row, col);
                //ajout des Kings
                if((row==0 || row == 7) && (col == 4)) 			   PiecesOnBoard[row][col] = new King	(this, (row==0)?Bp : Wp, row, col);
                
               
            }
        }
        
        
        
        //placing pieces    
        PlacingPieces();
        
        add(boardPanel);
        pack();
        setLocationRelativeTo(null); // afficher la fenetre en centre de l'ecran
        setVisible(true);
        
        Wp.setPiecesLeft(WpiecesOnBoard);
        Bp.setPiecesLeft(BpiecesOnBoard);
        this.whitePlayer = Wp; this.blackPlayer = Bp;
        
        
    }
    
    public void PlacingPieces() {
        for(int row = 0; row<BOARD_SIZE; row++) {
        	for (int col = 0; col < BOARD_SIZE; col++) {
        		ChessPiece piece = PiecesOnBoard[row][col];
        		if(piece != null) {
	        		boolean isplayerWhite = piece.getPlayer().PlayerisWhite();
	        		if(isplayerWhite) {this.WpiecesOnBoard.add(piece);} else {this.BpiecesOnBoard.add(piece);}
	        		
	        		JPanel cellPanel = (JPanel) boardPanel.getComponent(row * BOARD_SIZE + col);
	        		JLabel pawnLabel = new JLabel(piece.toString(), SwingConstants.CENTER);
	    		    pawnLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
	    		    pawnLabel.setForeground((isplayerWhite)?Color.WHITE : Color.BLACK );
	    		    pawnLabel.setFont(new Font("Calibri", Font.BOLD, 30));
	    		    cellPanel.add(pawnLabel);
        		}
        	}
        }
    }

	public ChessPiece PieceSelected(List<ChessPiece> listPieces, Move startMove) {
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
			return this.PieceSelected(WpiecesOnBoard, startMove);
		}
		else return this.PieceSelected(BpiecesOnBoard, startMove);
	}

	
	public void showRangePiece() {
		if(PieceSelected!= null) {
			//reinisitlisation des pieces que PieceSelected peut capturer à chaque click
			PieceSelected.emptyToCapture();
			List<Move> range = PieceSelected.PieceMoves();
			for(Move m : range) {
				JPanel cellPanel = (JPanel) boardPanel.getComponent(m.getY() * 8 + m.getX());
				cellPanel.setBackground(Color.RED);
				cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			}
		}
    }
	public void maskRange() {
		if(PieceSelected!= null) {
			PieceSelected.emptyToCapture();
			List<Move> range = PieceSelected.PieceMoves();
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
	
	public void mouseClicked(MouseEvent e) {
		maskRange();		
		Move moveClick =  Move.mouseCoordToBoardCoord(e.getX(), e.getY());
		
		//update the piece coordinates
		if(PieceSelected != null) {
			PiecesOnBoard[moveClick.getY()][moveClick.getX()] = PieceSelected;
			PiecesOnBoard[PieceSelected.getY()][PieceSelected.getX()] = null;
			PieceSelected.setX(moveClick.getX());
			PieceSelected.setX(moveClick.getY());
			//turn = (turn==0)?1 : 0;
		}
		PlacingPieces();
		PieceSelected = PlayerClickedPiece(moveClick);
		affiche2d(PiecesOnBoard);
		showRangePiece();
	}
	
	
    public Player getWhitePlayer() {return this.whitePlayer;}
	public Player getBlackPlayer() {return this.blackPlayer;}
	public String getGameMode() { return this.gameMode;}
	public List<ChessPiece> getWpiecesOnBoard(){ return this.WpiecesOnBoard;}
	public List<ChessPiece> getBpiecesOnBoard(){ return this.BpiecesOnBoard;}
	
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

