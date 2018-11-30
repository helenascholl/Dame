import processing.core.PApplet;

public class Grid extends PApplet {

    private int gridWidth;
    private int gridHeight;
    private int fieldWidth;
    private int fieldHeight;
    private int marginTopBottom;
    private int marginLeftRight;
    private boolean fieldsAreSquares;

    public Grid(int gridWidth, int gridHeight, int marginTopBottom, int marginLeftRight, boolean fieldsAreSquares) {

        setGridWidth(gridWidth);
        setGridHeight(gridHeight);
        setMarginTopBottom(marginTopBottom);
        setMarginLeftRight(marginLeftRight);
        setFieldsAreSquares(fieldsAreSquares);

        if (width > height && isFieldsAreSquares()) {

            setFieldWidth((height - getMarginTopBottom() * 2) / getGridHeight());
            setFieldHeight(getFieldWidth());
            setMarginTopBottom((width - getFieldWidth() * getGridWidth()) / 2);

        } else if (width < height && isFieldsAreSquares()) {

            setFieldWidth((width - marginLeftRight * 2) / gridWidth);
            setFieldHeight(getFieldWidth());
            setMarginTopBottom((height - getFieldHeight() * gridHeight) / 2);

        } else {

            setFieldWidth((width - getMarginLeftRight() * 2) / getGridWidth());
            setFieldHeight((height - getMarginTopBottom() * 2) / setGridHeight());
        }

    }

    //region Getter and Setter
    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public int getMarginTopBottom() {
        return marginTopBottom;
    }

    public void setMarginTopBottom(int marginTopBottom) {
        this.marginTopBottom = marginTopBottom;
    }

    public int getMarginLeftRight() {
        return marginLeftRight;
    }

    public void setMarginLeftRight(int marginLeftRight) {
        this.marginLeftRight = marginLeftRight;
    }

    public boolean isFieldsAreSquares() {
        return fieldsAreSquares;
    }

    public void setFieldsAreSquares(boolean fieldsAreSquares) {
        this.fieldsAreSquares = fieldsAreSquares;
    }
    //endregion

    public void print(int marginTopBottom, int marginLeftRight) {
        for (int i = 0; i < getGridWidth(); i++) {
            for (int j = 0; j < getGridHeight(); j++) {
                rect(getMarginLeftRight() + );
            }
        }
    }
}
