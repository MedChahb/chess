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
    
    private List<Move> whiteDefCell = new ArrayList<Move>();
    private List<Move> blackDefCell =new ArrayList<Move>();
    
    private String gameMode;
    private Player whitePlayer, blackPlayer;
    private JPanel boardPanel;
    
    private ChessPiece PieceSelected;
    private King wKing, bKing;
	private ChessPiece[][] PiecesOnBoard = new ChessPiece[8][8];
    private static int turn = 0; // si 0 c'est au White à jouer, 1 Black
    
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 80; 
    
    private Color whiteCell = new Color(232,215,184);
    private Color blackCell = new Color(181,136,99);
    
    
    public King kingTest = new King(this, new Player("moha", "white"), 2,3);
    
    
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
        
        
        
        

        
        WpiecesOnBoard = getPiecesLeft("white");
        BpiecesOnBoard = getPiecesLeft("black");
        
        Wp.setPiecesLeft(WpiecesOnBoard);
        Bp.setPiecesLeft(BpiecesOnBoard);
        
        wKing = getKing("white");
		bKing = getKing("black");
		
		whiteDefCell = getDefCell("white");
		blackDefCell = getDefCell("black");
        
        this.whitePlayer = Wp; this.blackPlayer = Bp;    
        
        
        
        //System.out.println(isSafe(kingTest));
        //placing pieces  
        //this.setPiece(3, 3, kingTest);
        PlacingPieces();
        pack();
        setLocationRelativeTo(null); // afficher la fenetre en centre de l'ecran
        setVisible(true);
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
	
	public List<Move> getDefCell(String color){
		List<Move> defCell = new ArrayList<>();
		List<ChessPiece> pieces = (color.equals("white"))?WpiecesOnBoard : BpiecesOnBoard;
		for(ChessPiece p : pieces) {
			List<Move> range = (p instanceof Pawn)? ((Pawn)p).PawnDefCell(): p.PieceMoves();
			for(Move move : range) {
				if(!move.moveInRange(defCell))
					defCell.add(move);
			}
		}
		return defCell;
	}
	
	public King getKing(String color) {
		List<ChessPiece> pieces = (color.equals("white"))?WpiecesOnBoard : BpiecesOnBoard;
		for(ChessPiece p : pieces) {
			if(p instanceof King) {
				return (King) p;
			}
		}
		// if king is captured (impo rules)
		return null;
	}
	
	
	// return la piece clické selon le tour de jeu.
	// null si pas de piece sur la souris
	public ChessPiece PlayerClickedPiece(Move startMove) {
		return this.PieceSelected(startMove);
	}

	public void highlightSelected(ChessPiece piece) {
		JPanel cellPanel = (JPanel) boardPanel.getComponent(piece.getY() * 8 + piece.getX());
		if(piece instanceof King) {
			cellPanel.setBackground(Color.BLUE);
		}
		else cellPanel.setBackground(Color.GREEN);
	}
	
	public void maskHighlight(ChessPiece piece) {
		if(piece!= null) {
			JPanel cellPanel = (JPanel) boardPanel.getComponent(piece.getY() * 8 + piece.getX());
			if ((piece.getY() + piece.getX()) % 2 == 0) { 
	            cellPanel.setBackground(whiteCell);
	        } else {
	            cellPanel.setBackground(blackCell);
	        }
		}
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
	
	public void movePiece2D(ChessPiece piece, Move endMove) {
		PiecesOnBoard[piece.getY()][piece.getX()] = null;
		piece.setX(endMove.getX());
		piece.setY(endMove.getY());
		PiecesOnBoard[endMove.getY()][endMove.getX()] = piece;
	}
	
	public void movePiece(ChessPiece piece, Move endMove) {
		if((!PieceSelected.getPlayer().PlayerisWhite() ^ turn == 0)&& validMove(piece, endMove)) {
			movePiece2D(piece, endMove);
			PlacingPieces();
			turn = (turn==0)?1:0;
			
			// en cas de capture :
	        WpiecesOnBoard = getPiecesLeft("white");
	        BpiecesOnBoard = getPiecesLeft("black");
	        whitePlayer.setPiecesLeft(WpiecesOnBoard);
	        blackPlayer.setPiecesLeft(BpiecesOnBoard);
	        
			//--
			if(piece instanceof Pawn) {
				((Pawn) piece).hasMoved();
			}
			if(piece instanceof King) {
				((King) piece).Moved();
			}
			if(piece instanceof Rook) {
				((Rook) piece).Moved();
			}
			String title = (turn == 0)?String.format("Chess : %s's turn (white)", whitePlayer.getPlayerName())
					: String.format("Chess : %s's turn (black)", blackPlayer.getPlayerName());
			setTitle(title);
		}
		
		
		
		//m-à-j des att
		wKing = getKing("white");
		wKing.Check(wKing.kingOnCapture());
		
		bKing = getKing("black");
		bKing.Check(bKing.kingOnCapture());
		
		whiteDefCell = getDefCell("white");
		blackDefCell = getDefCell("black");

    }
	// kt7t king f move okatchuf wach aykun f check
	public boolean isSafe(King king) {
		setPiece(2, 3, king);// khdama kt7t kingTest f 2,3
		// ncheckiw db wch kingTest fih check wla la
		setPiece(king.getY(), king.getX(), null); // n7ydo kingTest li zdna
		return king.kingOnCapture();
	}



	public void mouseClicked(MouseEvent e) {
		maskRange(PieceSelected);
		maskHighlight(PieceSelected);
		maskHighlight(wKing); maskHighlight(bKing);
		Move moveClick =  Move.mouseCoordToBoardCoord(e.getX(), e.getY());
		// left click -> select the piece (ready to move + show range + detect what to capture)
		if (e.getButton() == MouseEvent.BUTTON1) {	
			PieceSelected = PlayerClickedPiece(moveClick);
			if(PieceSelected!= null) {
				if((PieceSelected.getPlayer().PlayerisWhite() ^ turn == 1)) {
					highlightSelected(PieceSelected);
					showRangePiece(PieceSelected);
				}
			}
		}
		//right click -> move the selected piece(or capture whit it)
		else if (e.getButton() == MouseEvent.BUTTON3) {
			Move moveTo =  Move.mouseCoordToBoardCoord(e.getX(), e.getY());
			if(PieceSelected != null) {
				
				
				// detecte le moment roquer
				if(PieceSelected instanceof King) {
					King king = ((King)PieceSelected);
					int xOrg = king.getX();
					int yOrg = king.getY();
					if(king.CastledLeft(moveTo) && !king.getMoved() && getPieceOnBoard(moveTo)==null) {
						//khasni nakhod rook li an7rk m3a lking
						ChessPiece rook = getPieceOnBoard(new Move(0, (turn==0)?7 : 0));
						getPiecesOnBoard()[yOrg][xOrg] = null;
						getPiecesOnBoard()[moveTo.getY()][moveTo.getX()] = new King(this, (turn==0)?whitePlayer : blackPlayer, moveTo.getY(), moveTo.getX());
						//king.setX(moveTo.getX()); king.setY(moveTo.getY());
						movePiece(rook, new Move(3, (turn==0)?7 : 0));
					}
					if(king.CastledRight(moveTo) && !king.getMoved() && getPieceOnBoard(moveTo)==null) {
						ChessPiece rook = getPieceOnBoard(new Move(7, (turn==0)?7 : 0));
						getPiecesOnBoard()[yOrg][xOrg] = null;
						getPiecesOnBoard()[moveTo.getY()][moveTo.getX()] = new King(this, (turn==0)?whitePlayer : blackPlayer, moveTo.getY(), moveTo.getX());
						//king.setX(moveTo.getX()); king.setY(moveTo.getY());
						movePiece(rook, new Move(5, (turn==0)?7 : 0));
					}
				}
				
				
					//not inCheck
				if(!wKing.isInCheck()) {
					//update the piece coordinates
					movePiece(PieceSelected, moveClick);
				}
				//in check
				else {
					//bug in discovered checks
					if(PieceSelected.equals(wKing) && true && true)// pieces that can move when check
						movePiece(PieceSelected, moveClick);
				}
				
			}
			PieceSelected = null;
		}
		if(wKing.isInCheck()) {
			highlightSelected(wKing);
		}
		if(bKing.isInCheck()) {
			highlightSelected(bKing);
		}
		if(PieceSelected!=null)
			System.out.println(PieceSelected.getX());
		affiche2d(PiecesOnBoard);
		
	}
		
	
	
    public Player getWhitePlayer() {return this.whitePlayer;}
	public Player getBlackPlayer() {return this.blackPlayer;}
	public String getGameMode() { return this.gameMode;}
	public List<ChessPiece> getWpiecesOnBoard(){ return this.WpiecesOnBoard;}
	public List<ChessPiece> getBpiecesOnBoard(){ return this.BpiecesOnBoard;}
	public List<Move> getWhiteDefCell(){ return this.whiteDefCell;}
	public List<Move> getBlackDefCell(){ return this.blackDefCell;}
	public ChessPiece[][] getPiecesOnBoard(){ return this.PiecesOnBoard;}
	public ChessPiece getPieceOnBoard(Move move) { return (PieceSelected.moveInBoard(move))? PiecesOnBoard[move.getY()][move.getX()] : null;}
	public void setPiecesOnBoard(ChessPiece[][] b){ this.PiecesOnBoard = b;}
	
	//préc of x and y are valid
	public void setPiece(int y, int x, ChessPiece p) { this.PiecesOnBoard[y][x] = p;}
	

	
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

