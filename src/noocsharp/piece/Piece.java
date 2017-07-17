package noocsharp.piece;

import noocsharp.Chessboard;
import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nihal on 6/30/2017.
 */
public class Piece {

    public boolean hasMoved;
    public Color color;
    public Tuple<Integer, Integer> pos;

    public Piece(Color color, Tuple<Integer, Integer> pos) {
        this.hasMoved = false;
        this.color = color;
        this.pos =  pos;
    }

    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        return new HashSet<>();
    }

    public void setPos(Tuple<Integer, Integer> newPos) {
        pos = newPos;
    }
}
