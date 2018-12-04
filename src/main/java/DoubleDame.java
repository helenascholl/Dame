public class DoubleDame extends Dame {

    @Override
    public void print(int fieldWidth, int fieldHeight, int marginTop, int marginLeft) {

        float x = marginLeft + getX() * fieldWidth + fieldWidth * 0.5f;
        float y = marginTop + getY() * fieldHeight + fieldHeight * 0.5f;

        if (getColor() == Color.BLACK) {
            fill(0);
        } else {
            fill(255);
        }

        ellipse(x, y, fieldWidth * 0.8f, fieldHeight * 0.8f);
        ellipse(x + fieldWidth * 0.1f, y + fieldHeight * 0.1f, fieldWidth * 0.6f, fieldHeight * 0.6f);

    }

}