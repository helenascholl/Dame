import processing.core.PApplet;

abstract public class Dame extends PApplet {

    private int x;
    private int y;
    private Direction direction;

    public Dame() {
        setDirection(Direction.NORTH_WEST);
        setX(0);
        setY(0);
    }

    public Dame(int x, int y, Direction direction) {
        setDirection(direction);
        setX(x);
        setY(y);
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    //endregion

    public void stepForward() {

        switch (getDirection()) {

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