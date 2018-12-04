public class SingleDame extends Dame {

    @Override
    public void print(int fieldWidth, int fieldHeight, int marginTop, int marginLeft) {

        if (getColor() == Color.BLACK) {
            fill(0);
        } else {
            fill(255);
        }

        ellipse(marginLeft + getX() * fieldWidth + fieldWidth * 0.5f,
                marginTop + getY() * fieldHeight + fieldHeight * 0.5f,
                fieldWidth * 0.8f,
                fieldHeight * 0.8f);

    }

}
