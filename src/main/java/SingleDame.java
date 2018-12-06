public class SingleDame extends Dame {

    SingleDame(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void print(int fieldWidth, int fieldHeight, int marginTop, int marginLeft) {

        if (getColor() == Color.BLACK) {
            fill(0);
        } else if (getColor() == Color.WHITE){
            fill(255);
        }

        ellipse(marginLeft + getX() * fieldWidth + fieldWidth * 0.5f,
                marginTop + getY() * fieldHeight + fieldHeight * 0.5f,
                fieldWidth * 0.8f,
                fieldHeight * 0.8f);

    }

}
