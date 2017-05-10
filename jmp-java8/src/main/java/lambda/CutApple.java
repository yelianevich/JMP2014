package lambda;

public class CutApple extends Apple {
    private int pieceNum;

    public CutApple(Apple apple, int pieceNum) {
        super(apple.getWeight(), apple.getColor(), apple.getCountry());
        this.pieceNum = pieceNum;
    }

    public int getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(int pieceNum) {
        this.pieceNum = pieceNum;
    }
}