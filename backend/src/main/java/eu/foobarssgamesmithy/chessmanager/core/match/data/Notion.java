package eu.foobarssgamesmithy.chessmanager.core.match.data;

import java.util.List;

public class Notion {

    public final static String MADE = "#";

    public final static String CHECK = "+";

    public final static String CAPTURE = "x";

    public final static String SHORT_CASTLE = "0-0";

    public final static String LONG_CASTLE = "0-0-0";

    public final static String EN_PASSANT = "e.p.";

    public final static List<String> FILES = List.of("a", "b", "c", "d", "e", "f", "g", "h");

    public final static List<String> RANKS = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    public final static String KING_PIECE = "K";

    public final static String QUEEN_PIECE = "Q";

    public final static String ROOK_PIECE = "R";

    public final static String KNIGHT_PIECE = "N";

    public final static String BISHOP_PIECE = "B";

    public final static List<String> PIECES = List.of(KING_PIECE, QUEEN_PIECE, ROOK_PIECE, KNIGHT_PIECE, BISHOP_PIECE);

}
