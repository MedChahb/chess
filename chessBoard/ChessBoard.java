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
    
    private Color whiteCell = new Color(232,215,184);
    private Color blackCell = new Color(181,136,99);
    
    
    
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
                add(boardPanel);
                
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
        pack();
        setLocationRelativeTo(null); // afficher la fenetre en centre de l'ecran
        setVisible(true);
        
        WpiecesOnBoard = getPiecesLeft("white");
        BpiecesOnBoard = getPiecesLeft("black");
        
        Wp.setPiecesLeft(WpiecesOnBoard);
        Bp.setPiecesLeft(BpiecesOnBoard);
        this.whitePlayer = Wp; this.blackPlayer = Bp;        
    }
    
    public List<ChessPiece> getPiecesLeft(String color){
    	List<ChessPiece> piecesLeft = new ArrayList<>();
    	for (int row = 0; row < BOARD_SIZE; row++) {
	        for (int col = 0; col < BOARD_SIZE; col++) {
	        	ChessPiece piece = PiecesOnBoard[row][col];
	        	if(piece != null) {
		        	if(piece.getPlayer().PlayerisWhite() && color.equals("white"))
		        		piecesLeft.add(piece);
		        	else if(!piece.getPlayer().PlayerisWhite() && color.equals("black"))
		        		piecesLeft.add(piece);
	        	}
	        }
    	}
    	return piecesLeft;
    }
    
    
	public void PlacingPieces() {
	    for (int row = 0; row < BOARD_SIZE; row++) {
	        for (int col = 0; col < BOARD_SIZE; col++) {
	            JPanel cellPanel = (JPanel) boardPanel.getComponent(row * BOARD_SIZE + col);
	            cellPanel.removeAll();
	            ChessPiece piece = PiecesOnBoard[row][col];
	            if (piece != null) {
	                boolean isPlayerWhite = piece.getPlayer().PlayerisWhite();
	                JLabel pieceLabel = new JLabel(piece.toString(), SwingConstants.CENTER);
	                pieceLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
	                pieceLabel.setForeground((isPlayerWhite) ? Color.WHITE : Color.BLACK);
	                pieceLabel.setFont(new Font("Calibri", Font.BOLD, 30));
	                cellPanel.add(pieceLabel);
	            }
	            cellPanel.revalidate();
	            cellPanel.repaint();
	        }
	    }
	    boardPanel.revalidate();
	    boardPanel.repaint();
	}

	public ChessPiece PieceSelected(Move startMove) {
		//cherche dans la liste la piece de coord startMove
		return PiecesOnBoard[startMove.getY()][startMove.getX()];
	}
	
	// return la piece clické selon le tour de jeu.
	// null si pas de piece sur la souris
	public ChessPiece PlayerClickedPiece(Move startMove) {
		return this.PieceSelected(startMove);
	}

	
	public void showRangePiece(ChessPiece piece) {
		if(piece!= null) {
			//reinisitlisation des pieces que PieceSelected peut capturer à chaque click
			PieceSelected.emptyToCapture();
			List<Move> range = piece.PieceMoves();
			for(Move m : range) {
				JPanel cellPanel = (JPanel) boardPanel.getComponent(m.getY() * 8 + m.getX());
				cellPanel.setBackground(Color.RED);
				cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			}
		}
    }
	public void maskRange(ChessPiece piece) {
		if(piece!= null) {
			piece.emptyToCapture();
			List<Move> range = piece.PieceMoves();
			for(Move m : range) {
				JPanel cellPanel = (JPanel) boardPanel.getComponent(m.getY() * 8 + m.getX());
				if ((m.getY() + m.getX()) % 2 == 0) { 
                    cellPanel.setBackground(whiteCell);
                } else {
                    cellPanel.setBackground(blackCell);
                }
				cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
				
			}
		}
		
	}
	
	
	public boolean validMove(ChessPiece piece, Move endMove) {
		for(Move move : piece.PieceMoves()) {
			if(move.getX() == endMove.getX() && move.getY() == endMove.getY()) {
				return true;
			}
		}
		return false;
	}
	
	
	public void movePiece(ChessPiece piece, Move endMove) {
		if((!PieceSelected.getPlayer().PlayerisWhite() ^ turn == 0)&& validMove(piece, endMove)) {
			PiecesOnBoard[piece.getY()][piece.getX()] = null;
			piece.setX(endMove.getX());
			piece.setY(endMove.getY());
			PiecesOnBoard[endMove.getY()][endMove.getX()] = piece;
			PlacingPieces();
			turn = (turn==0)?1:0;
			
			// en cas de capture :
			whitePlayer.setPiecesLeft(getPiecesLeft("white"));
			blackPlayer.setPiecesLeft(getPiecesLeft("black"));
			//--
			if(piece instanceof Pawn) {
				((Pawn) piece).hasMoved();
			}
		}
    }


	
	public void mouseClicked(MouseEvent e) {
		maskRange(PieceSelected);
		Move moveClick =  Move.mouseCoordToBoardCoord(e.getX(), e.getY());
		// left click -> select the piece (ready to move + show range + detect what to capture)
		if (e.getButton() == MouseEvent.BUTTON1) {	
			PieceSelected = PlayerClickedPiece(moveClick);
			if(PieceSelected!= null) {
				if((PieceSelected.getPlayer().PlayerisWhite() ^ turn == 1))
						showRangePiece(PieceSelected);
			}
		}
		//right click -> move the selected piece(or capture whit it)
		else if (e.getButton() == MouseEvent.BUTTON3) {
			if(PieceSelected != null) {
				//update the piece coordinates
				movePiece(PieceSelected, moveClick);
			}
			PieceSelected = null;
		}
		
		//affiche2d(PiecesOnBoard);
		
	}
		
	
	
    public Player getWhitePlayer() {return this.whitePlayer;}
	public Player getBlackPlayer() {return this.blackPlayer;}
	public String getGameMode() { return this.gameMode;}
	public List<ChessPiece> getWpiecesOnBoard(){ return this.WpiecesOnBoard;}
	public List<ChessPiece> getBpiecesOnBoard(){ return this.BpiecesOnBoard;}
	public ChessPiece[][] getPiecesOnBoard(){ return this.PiecesOnBoard;}
	

	
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

