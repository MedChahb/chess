package pieces;

import java.util.ArrayList;
import java.util.List;

import chessBoard.ChessBoard;
import player.Move;
import player.Player;

public class King extends ChessPiece {
    private boolean inCheck = false;
    
    private boolean hasMoved = false;// pour roquer
    private boolean hasCastled = false;

    public King(ChessBoard board, Player p, int y, int x) {
        super(board, p, y, x);
    }

    public String toString() {
        return "K";
    }

    @Override
    public List<Move> PieceMoves() {
        List<Move> Moves = new ArrayList<>();
        Move move;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    move = new Move(x_ + i, y_ + j);
                    if (cellTakenEnemy(move)) toCapture.add(move);
                    List<Move> defCell = (player_.PlayerisWhite()) ? board_.getBlackDefCell() : board_.getWhiteDefCell();
                    if (isValidMove(move) && !move.moveInRange(defCell) && true/* not move in protected cell*/ ) {
                        Moves.add(move);
                    }
                }
            }
        }
        if(canCastleLeft()) {
        	int ligne = player_.PlayerisWhite()? 7 : 0;
        	move = new Move(2,ligne);
        	Moves.add(move);
        }
        if(canCastleRight()) {
        	int ligne = player_.PlayerisWhite()? 7 : 0;
        	move = new Move(6,ligne);
        	Moves.add(move);
        }
        return Moves;
    }

    public boolean canCastleLeft() {
    	boolean noNeighbors;
    	ChessPiece[][] neibhobrs = board_.getPiecesOnBoard();
    	int ligne = player_.PlayerisWhite()? 7 : 0;
    	if(!hasMoved) {
    		List<Move> defCell = (player_.PlayerisWhite()) ? board_.getBlackDefCell() : board_.getWhiteDefCell();
    		noNeighbors = neibhobrs[ligne][1]==null && neibhobrs[ligne][2]==null && neibhobrs[ligne][3]==null;
    		ChessPiece rookLeft = neibhobrs[ligne][0];
    		boolean rookCan = (rookLeft instanceof Rook) && !((Rook)rookLeft).getHasMoved();
    		boolean isSafe = !(new Move(1,ligne)).moveInRange(defCell) && !(new Move(2,ligne)).moveInRange(defCell) && !(new Move(3,ligne)).moveInRange(defCell);
    		return rookCan && noNeighbors && isSafe;
    	}
    	return false;
    }
    
    public boolean canCastleRight() {
    	boolean noNeighbors;
    	ChessPiece[][] neibhobrs = board_.getPiecesOnBoard();
    	int ligne = player_.PlayerisWhite()? 7 : 0;
    	if(!hasMoved && !inCheck) {
    		List<Move> defCell = (player_.PlayerisWhite()) ? board_.getBlackDefCell() : board_.getWhiteDefCell();
    		noNeighbors = neibhobrs[ligne][6]==null && neibhobrs[ligne][5]==null;
    		ChessPiece rookRight = neibhobrs[ligne][7];
    		boolean rookCan = (rookRight instanceof Rook) && !((Rook)rookRight).getHasMoved();
    		boolean isSafe = !(new Move(5,ligne)).moveInRange(defCell) && !(new Move(6,ligne)).moveInRange(defCell);
    		return rookCan && noNeighbors && isSafe;
    	}
    	return false;
    }
    public void Moved() { hasMoved = true;}
    public void Castled() { hasCastled = true;}
    public boolean getCaslted() { return hasCastled;}
    public boolean getMoved() { return hasMoved;}
    public boolean isInCheck() {
        return this.inCheck;
    }
    

    
    public boolean CastledLeft(Move endMove) {
    	return (endMove.getY() == y_ && endMove.getX() == x_-2);
    }
    public boolean CastledRight(Move endMove) {
    	return (endMove.getY() == y_ && endMove.getX() == x_+2);
    }
    
    
    public void Check(boolean inCheck) {
        this.inCheck = inCheck;
    }

    // true if king is in danger in his position (color?)
    public boolean kingOnCapture() {
        List<ChessPiece> enemyPieces = (player_.PlayerisWhite()) ? board_.getBpiecesOnBoard() : board_.getWpiecesOnBoard();
        for (ChessPiece enemy : enemyPieces) {
            List<Move> test = (enemy instanceof Pawn) ? ((Pawn) enemy).PawnDefCell() : enemy.PieceMoves();
            for (Move range : test) {
                if (x_ == range.getX() && y_ == range.getY()) {
                    return true;
                }
            }
        }
        return false;
    }


    // kt7t king f move okatchuf wach aykun f check
    public boolean isSafe(Move endMove) {
    	return false;
    }
}
