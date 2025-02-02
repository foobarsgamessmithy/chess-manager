package eu.foobarssgamesmithy.chessmanager.core.match.data;

public enum Promotion {
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N");

    String shortName;

    Promotion(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName(){
        return this.shortName;
    }

    public static Promotion fromNotation(String piece) {
        return switch (piece) {
            case Notion.QUEEN_PIECE -> Promotion.QUEEN;
            case Notion.ROOK_PIECE -> Promotion.ROOK;
            case Notion.BISHOP_PIECE -> Promotion.BISHOP;
            case Notion.KNIGHT_PIECE -> Promotion.KNIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + piece);
        };
    }
}
