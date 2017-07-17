package noocsharp.piece;

import noocsharp.Chessboard;
import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;
import noocsharp.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by nihal on 6/30/2017.
 */
public class King extends Piece {
    private boolean inCheck = false;
    public King(Color color, Tuple<Integer, Integer> pos) {
        super(color, pos);
    }

    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        HashSet<Tuple<Integer, Integer>> influence = new HashSet<>();

        Tuple<Integer, Integer> ur = new Tuple<>(this.pos.x+1, this.pos.y+1);
        Tuple<Integer, Integer> u =  new Tuple<>(this.pos.x, this.pos.y+1);
        Tuple<Integer, Integer> ul = new Tuple<>(this.pos.x-1, this.pos.y+1);
        Tuple<Integer, Integer> l =  new Tuple<>(this.pos.x-1, this.pos.y);
        Tuple<Integer, Integer> dl = new Tuple<>(this.pos.x-1, this.pos.y-1);
        Tuple<Integer, Integer> d =  new Tuple<>(this.pos.x, this.pos.y-1);
        Tuple<Integer, Integer> dr = new Tuple<>(this.pos.x+1, this.pos.y-1);
        Tuple<Integer, Integer> r =  new Tuple<>(this.pos.x+1, this.pos.y);

        HashSet<Tuple<Integer, Integer>> positions = new HashSet<>(Arrays.asList(ur, u, ul, l, dl, d, dr, r));

        for (Iterator pi = positions.iterator(); pi.hasNext();) {
            Tuple<Integer, Integer> t = (Tuple) pi.next();

            if (!t.isPositive()) {
                pi.remove();
                continue;
            }
            for (Piece p : pieces) {
                if (!(p instanceof King)) {
                    if (p.color == Utilities.oppositeColor(this.color)) {
                        if (p.getInfluence(pieces, width, height).contains(t)) {
                            pi.remove();
                            break;
                        }
                    } else if (p.color == this.color && p.pos.x == t.x && p.pos.y == t.y) {
                        pi.remove();
                        break;
                    }
                }
            }
        }


        influence = positions;

        return influence;
    }

    public void setCheckStatus(boolean b) {
        inCheck = b;
    }

    public void updateCheckStatus(ArrayList<Piece> pieces, int width, int height) {
        boolean foundCheck = false;
        for (Piece p : pieces) {
            if (p.getInfluence(pieces, width, height).contains(this.pos)) {
                this.inCheck = true;
                foundCheck = true;
            }
        }

        if (!foundCheck) {
            this.inCheck = false;
        }
    }
}
