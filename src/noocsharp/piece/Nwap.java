package noocsharp.piece;

import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;

import java.util.ArrayList;
import java.util.HashSet;

import static noocsharp.utilities.Utilities.oppositeColor;
import static noocsharp.utilities.Utilities.orient;

/**
 * Created by nihal on 7/17/2017.
 */
public class Nwap extends Piece {
    public Nwap(Color color, Tuple<Integer, Integer> pos) {
        super(color, pos);
    }

    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        HashSet<Tuple<Integer, Integer>> influence = new HashSet<>();

        // makes sure position is inside the board
        if (this.pos.x >= 0 && this.pos.y >= 0 && this.pos.x < width && this.pos.y < height) {
            // checks square immediately in front of pawn
            /*
            if (chessboard.getChessboardArray()[position.x][position.y + orient(1, this.color)] instanceof Filler) {
                influence.add(new Tuple<>(position.x, position.y + orient(1, this.color)));
            }
            */
            boolean foundFront = false;
            boolean foundFrontRight = false;
            boolean foundFrontLeft = false;
            boolean found2Front = false;

            for (Piece p : pieces) {
                if (p.pos.x == this.pos.x && p.pos.y == this.pos.y - orient(1, this.color)) {
                    foundFront = true;
                }

                if (p.pos.x == this.pos.x + 1 && p.pos.y == this.pos.y + orient(1, this.color) && p.color == oppositeColor(this.color)) {
                    foundFrontRight = true;
                }

                if (p.pos.x == this.pos.x - 1 && p.pos.y == this.pos.y + orient(1, this.color) && p.color == oppositeColor(this.color)) {
                    foundFrontLeft = true;
                }

                if (p.pos.x == this.pos.x && p.pos.y == this.pos.y - orient(2, this.color)) {
                    found2Front = true;
                }

                if (foundFront && foundFrontRight && foundFrontLeft && found2Front) {
                    break;
                }
            }

            if (!foundFront) {
                int x = this.pos.x;
                int y = (((this.pos.y-orient(1, this.color))%height) + height)%height;
                influence.add(new Tuple<>(x, y));
            }

            if (foundFrontRight) {
                int x = this.pos.x+1;
                int y = (((this.pos.y-orient(1, this.color))%height) + height)%height;
                influence.add(new Tuple<>(x, y));
            }

            if (foundFrontLeft) {
                int x = this.pos.x-1;
                int y = (((this.pos.y-orient(1, this.color))%height) + height)%height;
                influence.add(new Tuple<>(x, y));
            }

            if (!found2Front && !hasMoved) {
                influence.add(new Tuple<>(this.pos.x, (this.pos.y-orient(2, this.color))));
            }
        }
        
        if (!this.hasMoved) {
            this.hasMoved = true;
        }
        return influence;
    }
}
