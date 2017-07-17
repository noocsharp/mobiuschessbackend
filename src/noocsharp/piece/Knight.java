package noocsharp.piece;

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
public class Knight extends Piece{
    public Knight(Color color, Tuple<Integer, Integer> pos) {
        super(color, pos);
    }

    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        HashSet<Tuple<Integer, Integer>> influence = new HashSet<>();

        Tuple<Integer, Integer> ur1 = new Tuple<>(this.pos.x+2, this.pos.y+1);
        Tuple<Integer, Integer> ul1 = new Tuple<>(this.pos.x-2, this.pos.y+1);
        Tuple<Integer, Integer> dl1 = new Tuple<>(this.pos.x-2, this.pos.y-1);
        Tuple<Integer, Integer> dr1 = new Tuple<>(this.pos.x+2, this.pos.y-1);

        Tuple<Integer, Integer> ur2 = new Tuple<>(this.pos.x+1, this.pos.y+2);
        Tuple<Integer, Integer> ul2 = new Tuple<>(this.pos.x+1, this.pos.y-2);
        Tuple<Integer, Integer> dl2 = new Tuple<>(this.pos.x-1, this.pos.y-2);
        Tuple<Integer, Integer> dr2 = new Tuple<>(this.pos.x-1, this.pos.y+2);

        HashSet<Tuple<Integer, Integer>> positions = new HashSet<>(Arrays.asList(ur1, ul1, dl1, dr1, ur2, ul2, dl2, dr2));

        ArrayList<Tuple<Integer, Integer>> blocked = new ArrayList<>();

        for (Iterator pi = pieces.iterator(); pi.hasNext();) {
            Piece p = (Piece) pi.next();
            for (Iterator ti = positions.iterator(); ti.hasNext();) {
                Tuple<Integer, Integer> t = (Tuple) ti.next();
                if (t.x >= 0 && t.y >= 0 && t.x < width && t.y < height) {
                    if (p.pos.x == t.x && p.pos.y == t.y) {
                        if (p.color == this.color) {
                            ti.remove();
                        }
                    }
                } else {
                    ti.remove();
                }
            }
        }

        influence = positions;

        return influence;
    }
}
