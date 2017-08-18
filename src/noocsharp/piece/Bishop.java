package noocsharp.piece;

import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;
import noocsharp.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nihal on 6/30/2017.
 */
public class Bishop extends Piece{
    public Bishop(Color color, Tuple<Integer, Integer> pos) {
        super(color, pos);
    }
    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        HashSet<Tuple<Integer, Integer>> influence = new HashSet<>();

        if (this.pos.x >= 0 && this.pos.y >= 0 && this.pos.x < width && this.pos.y < height) {

            boolean doneTopRight = false;
            boolean doneTopLeft = false;
            boolean doneBottomRight = false;
            boolean doneBottomLeft = false;

            for (int i = 1; i <= 7; i++) {
                int xP = this.pos.x+i;
                int xN = this.pos.x-i;

                int yP = (this.pos.y+i+height)%height;
                int yN = (this.pos.y-i+height)%height;

                Piece searchTopRight = Utilities.searchForPos(pieces, new Tuple<>(xP, yP));
                Piece searchTopLeft = Utilities.searchForPos(pieces, new Tuple<>(xN, yP));
                Piece searchBottomRight = Utilities.searchForPos(pieces, new Tuple<>(xP, yN));
                Piece searchBottomLeft = Utilities.searchForPos(pieces, new Tuple<>(xN, yN));

                if (!doneTopRight) {
                    if (this.pos.x+i >= 0 && this.pos.x+i < width) {
                        if (searchTopRight == null) {
                            influence.add(new Tuple<>(xP, yP));
                        } else {
                            if (searchTopRight.color == this.color) {
                                doneTopRight = true;
                            } else if (searchTopRight.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(xP, yP));
                                doneTopRight = true;
                            }
                        }
                    } else {
                        doneTopRight = true;
                    }
                }

                if (!doneTopLeft) {
                    if (this.pos.x-i >= 0 && this.pos.x-i < width) {
                        if (searchTopLeft == null) {
                            influence.add(new Tuple<>(xN, yP));
                        } else {
                            if (searchTopLeft.color == this.color) {
                                doneTopLeft = true;
                            } else if (searchTopLeft.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(xN, yP));
                                doneTopLeft = true;
                            }
                        }
                    } else {
                        doneTopLeft = true;
                    }
                }

                if (!doneBottomRight) {
                    if (this.pos.x+i >= 0 && this.pos.x+i < width) {
                        if (searchBottomRight == null) {
                            influence.add(new Tuple<>(xP, yN));
                        } else {
                            if (searchBottomRight.color == this.color) {
                                doneBottomRight = true;
                            } else if (searchBottomRight.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(xP, yN));
                                doneBottomRight = true;
                            }
                        }
                    } else {
                        doneBottomRight = true;
                    }
                }

                if (!doneBottomLeft) {
                    if (this.pos.x-i >= 0 && this.pos.x-i < width) {
                        if (searchBottomLeft == null) {
                            influence.add(new Tuple<>(xN, yN));
                        } else {
                            if (searchBottomLeft.color == this.color) {
                                doneBottomLeft = true;
                            } else if (searchBottomLeft.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(xN, yN));
                                doneBottomLeft = true;
                            }
                        }
                    } else {
                        doneBottomLeft = true;
                    }
                }
            }
        }

        return influence;
    }
}
