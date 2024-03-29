package noocsharp;

import noocsharp.piece.*;
import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;
import noocsharp.utilities.Utilities;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by nihal on 6/30/2017.
 *
 * Coordinate system is bottom left to top right, where first coordinate is x and second is y
 */
public class Chessboard {
    //Piece[][] chessboard;
    ArrayList<Piece> pieces;
    boolean inCheck = false;
    boolean whiteTurn = true;
    public final int WIDTH;
    public final int HEIGHT;

    public Chessboard (int width, int height) {
        // should be 8 by 14
        this.WIDTH = width;
        this.HEIGHT = height;
        //this.chessboard = new Piece[this.WIDTH][this.HEIGHT];
        pieces = new ArrayList<>();
        this.reset();
    }

    public void reset() {
        pieces.add(new   Rook(Color.WHITE, new Tuple<>(0, 3)));
        pieces.add(new Knight(Color.WHITE, new Tuple<>(1, 3)));
        pieces.add(new Bishop(Color.WHITE, new Tuple<>(2, 3)));
        pieces.add(new  Queen(Color.WHITE, new Tuple<>(3, 3)));
        pieces.add(new   King(Color.WHITE, new Tuple<>(4, 3)));
        pieces.add(new Bishop(Color.WHITE, new Tuple<>(5, 3)));
        pieces.add(new Knight(Color.WHITE, new Tuple<>(6, 3)));
        pieces.add(new   Rook(Color.WHITE, new Tuple<>(7, 3)));

        pieces.add(new   Rook(Color.BLACK, new Tuple<>(0, 10)));
        pieces.add(new Knight(Color.BLACK, new Tuple<>(1, 10)));
        pieces.add(new Bishop(Color.BLACK, new Tuple<>(2, 10)));
        pieces.add(new  Queen(Color.BLACK, new Tuple<>(3, 10)));
        pieces.add(new   King(Color.BLACK, new Tuple<>(4, 10)));
        pieces.add(new Bishop(Color.BLACK, new Tuple<>(5, 10)));
        pieces.add(new Knight(Color.BLACK, new Tuple<>(6, 10)));
        pieces.add(new   Rook(Color.BLACK, new Tuple<>(7, 10)));

        for (int i = 0; i<8; i++) {
            pieces.add(new Pawn(Color.WHITE, new Tuple<>(i, 4)));
            pieces.add(new Pawn(Color.BLACK, new Tuple<>(i, 9)));

            pieces.add(new Nwap(Color.WHITE, new Tuple<>(i, 2)));
            pieces.add(new Nwap(Color.BLACK, new Tuple<>(i, 11)));
        }
    }

    public void addPiece(Piece p) {
        pieces.add(p);
    }

    public void removePiece(Tuple<Integer, Integer> pos) {
        for (Iterator<Piece> iterator = pieces.iterator(); iterator.hasNext();) {
            Piece p = iterator.next();
            if (p.pos.x == pos.x && p.pos.y == pos.y) {
                System.out.println("Removed " + p.getClass() + " at position " + (p.pos.toString()));
                iterator.remove();
            }
        }
    }

    // Returns true if move is successful, false if move is invalid
    public boolean makeMove(Tuple<Integer, Integer> pos, Tuple<Integer, Integer> des) {
        int desPieceIndex = Utilities.indexFromPos(pieces, des);
        int pieceIndex = Utilities.indexFromPos(pieces, pos);
        if (moveIsValid(pos, des)) {
            pieces.get(pieceIndex).setPos(des);
            if (desPieceIndex != -1) {
                pieces.remove(desPieceIndex);
            }
            updateCheckStatus();
            return true;
        }
        return false;
    }

    private boolean moveIsValid(Tuple<Integer, Integer> pos, Tuple<Integer, Integer> des) {
        Piece p = Utilities.searchForPos(pieces, pos);
        if (p.getInfluence(pieces, WIDTH, HEIGHT).contains(des)) {
            return true;
        } else {
            return false;
        }
    }

    // Checks if king is in check
    private void updateCheckStatus() {

        for (int i = 0; i<pieces.size(); i++) {
            Piece p = pieces.get(i);
            if (p instanceof King) {
                ((King) p).updateCheckStatus(pieces, WIDTH, HEIGHT);
            }
        }
    }

    public ArrayList<Piece> getPiecesArray() { return pieces; }


}
