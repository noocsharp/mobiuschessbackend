package noocsharp.piece;

import noocsharp.utilities.Color;
import noocsharp.utilities.Tuple;
import noocsharp.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by nihal on 6/30/2017.
 */
public class Queen extends Piece{
    public Queen(Color color, Tuple<Integer, Integer> pos) {
        super(color, pos);
    }

    public HashSet<Tuple<Integer, Integer>> getInfluence(ArrayList<Piece> pieces, int width, int height) {
        HashSet<Tuple<Integer, Integer>> influence = new HashSet<>();

        if (this.pos.x >= 0 && this.pos.y >= 0 && this.pos.x < width && this.pos.y < height) {

            boolean doneUp = false;
            boolean doneDown = false;
            boolean doneLeft = false;
            boolean doneRight = false;

            boolean doneTopRight = false;
            boolean doneTopLeft = false;
            boolean doneBottomRight = false;
            boolean doneBottomLeft = false;

            for (int i = 1; i <= Utilities.max(new Integer[]{this.pos.x, this.pos.y, width - this.pos.x, height - this.pos.y}); i++) {
                Piece searchUp = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x, this.pos.y+i));
                Piece searchDown = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x, this.pos.y-i));
                Piece searchLeft = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x-i, this.pos.y));
                Piece searchRight = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x+i, this.pos.y));

                Piece searchTopRight = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x+i, this.pos.y+i));
                Piece searchTopLeft = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x-1, this.pos.y+i));
                Piece searchBottomRight = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x+i, this.pos.y-1));
                Piece searchBottomLeft = Utilities.searchForPos(pieces, new Tuple<>(this.pos.x-i, this.pos.y-1));

                if (!doneUp) {
                    if (this.pos.y+i >= 0 && this.pos.y+i < height) {
                        if (searchUp == null) {
                            influence.add(new Tuple<>(this.pos.x, this.pos.y+i));
                        } else {
                            if (searchUp.color == this.color) {
                                doneUp = true;
                            } else if (searchUp.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x, this.pos.y+i));
                                doneUp = true;
                            }
                        }
                    } else {
                        doneUp = true;
                    }
                }

                if (!doneDown) {
                    if (this.pos.y-i >= 0 && this.pos.y-i < height) {
                        if (searchDown == null) {
                            influence.add(new Tuple<>(this.pos.x, this.pos.y+i));
                        } else {
                            if (searchDown.color == this.color) {
                                doneDown = true;
                            } else if (searchDown.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x, this.pos.y-i));
                                doneDown = true;
                            }
                        }
                    } else {
                        doneDown = true;
                    }
                }

                if (!doneRight) {
                    if (this.pos.x+i >= 0 && this.pos.x+i < height) {
                        if (searchRight == null) {
                            influence.add(new Tuple<>(this.pos.x+1, this.pos.y));
                        } else {
                            if (searchRight.color == this.color) {
                                doneRight = true;
                            } else if (searchRight.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x+1, this.pos.y));
                                doneRight = true;
                            }
                        }
                    } else {
                        doneRight = true;
                    }
                }

                if (!doneLeft) {
                    if (this.pos.x-i >= 0 && this.pos.x-i < height) {
                        if (searchLeft == null) {
                            influence.add(new Tuple<>(this.pos.x-1, this.pos.y));
                        } else {
                            if (searchLeft.color == this.color) {
                                doneLeft = true;
                            } else if (searchLeft.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x-1, this.pos.y));
                                doneLeft = true;
                            }
                        }
                    } else {
                        doneLeft = true;
                    }
                }

                if (!doneTopRight) {
                    if (this.pos.y+i >= 0 && this.pos.y+i < height && this.pos.x+i >= 0 && this.pos.x+i < width) {
                        if (searchTopRight == null) {
                            influence.add(new Tuple<>(this.pos.x+i, this.pos.y+i));
                        } else {
                            if (searchTopRight.color == this.color) {
                                doneTopRight = true;
                            } else if (searchTopRight.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x+i, this.pos.y+i));
                                doneTopRight = true;
                            }
                        }
                    } else {
                        doneTopRight = true;
                    }
                }

                if (!doneTopLeft) {
                    if (this.pos.y+i >= 0 && this.pos.y+i < height && this.pos.x-i >= 0 && this.pos.x-i < width) {
                        if (searchTopLeft == null) {
                            influence.add(new Tuple<>(this.pos.x-i, this.pos.y+i));
                        } else {
                            if (searchTopLeft.color == this.color) {
                                doneTopLeft = true;
                            } else if (searchTopLeft.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x-i, this.pos.y-i));
                                doneTopLeft = true;
                            }
                        }
                    } else {
                        doneTopLeft = true;
                    }
                }

                if (!doneBottomRight) {
                    if (this.pos.y-i >= 0 && this.pos.y-i < height && this.pos.x+i >= 0 && this.pos.x+i < width) {
                        if (searchBottomRight == null) {
                            influence.add(new Tuple<>(this.pos.x+i, this.pos.y-i));
                        } else {
                            if (searchBottomRight.color == this.color) {
                                doneBottomRight = true;
                            } else if (searchBottomRight.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x+i, this.pos.y-i));
                                doneBottomRight = true;
                            }
                        }
                    } else {
                        doneBottomRight = true;
                    }
                }

                if (!doneBottomLeft) {
                    if (this.pos.y-i >= 0 && this.pos.y-i < height && this.pos.x-i >= 0 && this.pos.x-i < width) {
                        if (searchBottomLeft == null) {
                            influence.add(new Tuple<>(this.pos.x-i, this.pos.y-i));
                        } else {
                            if (searchBottomLeft.color == this.color) {
                                doneBottomLeft = true;
                            } else if (searchBottomLeft.color == Utilities.oppositeColor(this.color)) {
                                influence.add(new Tuple<>(this.pos.x-i, this.pos.y-i));
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
