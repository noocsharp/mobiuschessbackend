package noocsharp.utilities;

import noocsharp.Chessboard;
import noocsharp.piece.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by nihal on 6/30/2017.
 */
public class Utilities {

    // Orients a vector based off of what color the piece is (White needs to move up, black needs to move down)
    public static int orient(int i, Color color) {
        if (color == Color.WHITE) {
            return i;
        } else if (color == Color.BLACK) {
            return -i;
        }
        return 0;
    }

    // returns black if white and white if black (necessary because Color.NONE)
    public static Color oppositeColor(Color color) {
        if (color == Color.WHITE) {
            return Color.BLACK;
        } else if (color == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.NONE;
    }

    // returns piece if piece's position is tuple
    public static Piece searchForPos(ArrayList<Piece> pieces, Tuple<Integer, Integer> pos) {
        for (Piece p : pieces) {
            if (p.pos.x == pos.x && p.pos.y == pos.y) {
                return p;
            }
        }
        return null;
    }

    public static int indexFromPos(ArrayList<Piece> pieces, Tuple<Integer, Integer> pos) {
        for (int i = 0; i < pieces.size(); i++) {
            Piece p = pieces.get(i);
            if (p.pos.x == pos.x && p.pos.y == pos.y) {
                return i;
            }
        }
        return -1;
    }

    // finds the max int in an array
    public static int max(Integer[] array) {
        int max = array[1];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }


   /* public static ArrayList<Tuple<Integer, Integer>> pawnInfluence(ArrayList<Piece> pieces, Piece piece, int width, int height) {
        ArrayList<Tuple<Integer, Integer>> influence = new ArrayList<>();

        // makes sure position is inside the board
        if (piece.pos.x >= 0 && piece.pos.y >= 0 && piece.pos.x < width && piece.pos.y < chessboard.HEIGHT) {
            // checks square immediately in front of pawn
            if (chessboard.getChessboardArray()[position.x][position.y + Utilities.orient(1, piece.color)] instanceof Filler) {
                influence.add(new Tuple<>(position.x, position.y + Utilities.orient(1, piece.color)));
            }

            // enclosed in try-catch block because will throw error if pawn is on the edge, could change it to make it less pythonic
            try {
                // checks square in front and right of the pawn
                if (chessboard.getChessboardArray()[position.x + 1][position.y + Utilities.orient(1, piece.color)].color == Utilities.oppositeColor(piece.color)) {
                    influence.add(new Tuple<>(position.x + 1, position.y + Utilities.orient(1, piece.color)));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            }

            try {
                // checks square in front and left of the pawn
                if (chessboard.getChessboardArray()[position.x - 1][position.y + Utilities.orient(1, piece.color)].color == Utilities.oppositeColor(piece.color)) {
                    System.out.println("x-1 is good.");
                    influence.add(new Tuple<>(position.x - 1, position.y + Utilities.orient(1, piece.color)));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            }

            // check if two-square move is possible
            if (!piece.hasMoved) {
                if (chessboard.getChessboardArray()[position.x][position.y + Utilities.orient(2, piece.color)] instanceof Filler) {
                    influence.add(new Tuple<>(position.x, position.y + Utilities.orient(2, piece.color)));
                }
                piece.hasMoved = true;
            }

            // en passant needs to be implemented
        }
        return influence;
    }

    private static ArrayList<Tuple<Integer, Integer>> rookInfluence(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> influence = new ArrayList<>();

        if (position.x >= 0 && position.y >= 0 && position.x < chessboard.WIDTH && position.y < chessboard.HEIGHT) {
            // checks all squares to the left for influence
            for (int xn = position.x - 1; xn >= 0; xn--) {
                if (chessboard.getChessboardArray()[xn][position.y] instanceof Filler) {
                    influence.add(new Tuple<>(xn, position.y));
                } else if (chessboard.getChessboardArray()[xn][position.y].color == piece.color) {
                    break;
                } else if (chessboard.getChessboardArray()[xn][position.y].color == Utilities.oppositeColor(piece.color)) {
                    influence.add(new Tuple<>(xn, position.y));
                    break;
                }
            }

            // checks all squares to the right for influence
            for (int xp = position.x + 1; xp < chessboard.WIDTH; xp++) {
                if (chessboard.getChessboardArray()[xp][position.y] instanceof Filler) {
                    influence.add(new Tuple<>(xp, position.y));
                } else if (chessboard.getChessboardArray()[xp][position.y].color == piece.color) {
                    break;
                } else if (chessboard.getChessboardArray()[xp][position.y].color == Utilities.oppositeColor(piece.color)) {
                    influence.add(new Tuple<>(xp, position.y));
                    break;
                }
            }

            // checks all squares below for influence
            for (int yn = position.x - 1; yn >= 0; yn--) {
                if (chessboard.getChessboardArray()[position.x][yn] instanceof Filler) {
                    influence.add(new Tuple<>(position.x, yn));
                } else if (chessboard.getChessboardArray()[position.x][yn].color == piece.color) {
                    break;
                } else if (chessboard.getChessboardArray()[position.x][yn].color == Utilities.oppositeColor(piece.color)) {
                    influence.add(new Tuple<>(position.x, yn));
                    break;
                }
            }

            // checks all squares above for influence
            for (int yp = position.x + 1; yp < chessboard.WIDTH; yp++) {
                if (chessboard.getChessboardArray()[position.x][yp] instanceof Filler) {
                    influence.add(new Tuple<>(position.x, yp));
                } else if (chessboard.getChessboardArray()[position.x][yp].color == piece.color) {
                    break;
                } else if (chessboard.getChessboardArray()[position.x][yp].color == Utilities.oppositeColor(piece.color)) {
                    influence.add(new Tuple<>(position.x, yp));
                    break;
                }
            }
        }

        return influence;
    }

    private static ArrayList<Tuple<Integer, Integer>> bishopInfluence(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> influence = new ArrayList<>();

        if (position.x >= 1 && position.y >= 0 && position.x < chessboard.WIDTH && position.y < chessboard.HEIGHT) {
            //top right
            for (int ur = 1; ur <= ((position.x > position.y) ? chessboard.WIDTH - position.x - 1 : chessboard.HEIGHT - position.y - 1); ur++) {
                try {
                    if (chessboard.getChessboardArray()[position.x + ur][position.y + ur] instanceof Filler) {
                        influence.add(new Tuple<>(position.x + ur, position.y + ur));
                    } else if (chessboard.getChessboardArray()[position.x + ur][position.y + ur].color == piece.color) {
                        break;
                    } else if (chessboard.getChessboardArray()[position.x + ur][position.y + ur].color == Utilities.oppositeColor(piece.color)) {
                        influence.add(new Tuple<>(position.x + ur, position.y + ur));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

            // bottom left
            for (int bl = 1; bl <= ((position.x < position.y) ? position.x : position.y); bl++) {
                try {
                    if (chessboard.getChessboardArray()[position.x - bl][position.y - bl] instanceof Filler) {
                        influence.add(new Tuple<>(position.x - bl, position.y - bl));
                    } else if (chessboard.getChessboardArray()[position.x - bl][position.y - bl].color == piece.color) {
                        break;
                    } else if (chessboard.getChessboardArray()[position.x - bl][position.y - bl].color == Utilities.oppositeColor(piece.color)) {
                        influence.add(new Tuple<>(position.x - bl, position.y - bl));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

            //top left
            for (int tl = 1; tl <= ((chessboard.HEIGHT - position.x - 1 > position.y) ? position.x : chessboard.HEIGHT - position.y - 1); tl++) {
                try {
                    if (chessboard.getChessboardArray()[position.x - tl][position.y + tl] instanceof Filler) {
                        influence.add(new Tuple<>(position.x - tl, position.y + tl));
                    } else if (chessboard.getChessboardArray()[position.x - tl][position.y + tl].color == piece.color) {
                        break;
                    } else if (chessboard.getChessboardArray()[position.x - tl][position.y + tl].color == Utilities.oppositeColor(piece.color)) {
                        influence.add(new Tuple<>(position.x - tl, position.y + tl));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

            //bottom right
            for (int br = 1; br <= ((position.x > position.y) ? chessboard.WIDTH - position.x - 1 : position.y); br++) {
                try {
                    if (chessboard.getChessboardArray()[position.x + br][position.y - br] instanceof Filler) {
                        influence.add(new Tuple<>(position.x + br, position.y - br));
                    } else if (chessboard.getChessboardArray()[position.x + br][position.y - br].color == piece.color) {
                        break;
                    } else if (chessboard.getChessboardArray()[position.x + br][position.y - br].color == Utilities.oppositeColor(piece.color)) {
                        influence.add(new Tuple<>(position.x + br, position.y - br));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return influence;
    }

    private static ArrayList<Tuple<Integer, Integer>> queenInfluence(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> bList = bishopInfluence(chessboard, position, piece);
        ArrayList<Tuple<Integer, Integer>> rList = rookInfluence(chessboard, position, piece);
        bList.addAll(rList);
        return bList;

    }

    private static ArrayList<Tuple<Integer, Integer>> knightInfluence(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> influence = new ArrayList<>();

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y + 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 1, position.y + 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y - 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 1, position.y - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x - 1][position.y - 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x - 1, position.y - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x - 1][position.y + 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x - 1, position.y + 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 2][position.y + 1].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 2, position.y + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 2][position.y - 1].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 2, position.y - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y - 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 1, position.y - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y - 2].color == piece.color)) {
                influence.add(new Tuple<Integer, Integer>(position.x + 1, position.y - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return influence;
    }

    private static ArrayList<Tuple<Integer, Integer>> kingInfluence(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> influence = new ArrayList<>();
        ArrayList<Tuple<Integer, Integer>> moves = getKingMoves(chessboard, position, piece);
        System.out.print("Before transformation: ");
        System.out.println(moves);

        for (int i = 0; i < chessboard.WIDTH; i++) {
            for (int j = 0; j < chessboard.HEIGHT; j++) {
                for (Tuple<Integer, Integer> pos : moves) {
                    if (chessboard.getChessboardArray()[i][j].color == oppositeColor(piece.color)) {

                        if (Utilities.getInfluence(chessboard, new Tuple<>(i, j)).contains(pos) && !(chessboard.getChessboardArray()[i][j] instanceof King)) {
                            moves.remove(pos);
                        }
                    }
                }
            }
        }

        System.out.print("After transformation: ");
        System.out.println(moves);
        influence = moves;
        return influence;
    }

    private static ArrayList<Tuple<Integer, Integer>> getKingMoves(Chessboard chessboard, Tuple<Integer, Integer> position, Piece piece) {
        ArrayList<Tuple<Integer, Integer>> moves = new ArrayList<>();

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x + 1, position.y));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y + 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x + 1, position.y + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x][position.y + 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x, position.y + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x - 1][position.y + 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x - 1, position.y + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x - 1][position.y].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x - 1, position.y));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x - 1][position.y - 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x - 1, position.y - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x][position.y - 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x, position.y - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        try {
            if (!(chessboard.getChessboardArray()[position.x + 1][position.y - 1].color == piece.color)) {
                moves.add(new Tuple<Integer, Integer>(position.x + 1, position.y - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return moves;
    }*/
}
