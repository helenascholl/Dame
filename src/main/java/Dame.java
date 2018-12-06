import processing.core.PApplet;

abstract public class Dame extends PApplet {

    private int x;
    private int y;
    private Color color;

    public Dame(int x, int y, Color color) {
        setX(x);
        setY(y);
        setColor(color);
    }

    //region Getter and Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    //endregion

    public void move(Direction direction) {

        switch (direction) {

            case NORTH_WEST:
                setY(getY() - 1);
                setX(getY() - 1);
                break;

            case NORTH_EAST:
                setY(getY() - 1);
                setX(getX() + 1);
                break;

            case SOUTH_EAST:
                setY(getY() + 1);
                setX(getX() + 1);
                break;

            case SOUTH_WEST:
                setY(getY() + 1);
                setX(getX() - 1);
                break;

        }

    }

    public abstract void print(int fieldWidth, int fieldHeight, int marginTop, int marginLeft);

}