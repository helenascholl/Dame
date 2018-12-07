import processing.core.PApplet;

public class Grid extends PApplet {

    private int gridWidth;
    private int gridHeight;
    private int fieldSize;
    private int marginTopBottom;
    private int marginLeftRight;
    private int[] fieldColors;
    private int selectedFieldColor;
    private int hoverColor;
    public int[][] colors;

    public Grid(int gridWidth, int gridHeight, int marginTopBottom, int marginLeftRight, int[] fieldColors, int selectedFieldColor, int hoverColor) {

        setGridWidth(gridWidth);
        setGridHeight(gridHeight);
        setMarginTopBottom(marginTopBottom);
        setMarginLeftRight(marginLeftRight);
        setFieldColors(fieldColors);
        setSelectedFieldColor(selectedFieldColor);
        setHoverColor(hoverColor);

        colors = new int[getGridWidth()][getGridHeight()];

        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                setField(i, j);
            }
        }

    }

    public void setField(int x, int y) {
        if (x % 2 == 0) {
            if (y % 2 == 0) {
                colors[x][y] = getFieldColors()[0];
            } else {
                colors[x][y] = getFieldColors()[1];
            }
        } else {
            if (y % 2 == 0) {
                colors[x][y] = getFieldColors()[1];
            } else {
                colors[x][y] = getFieldColors()[0];
            }
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

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
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

    public int[] getFieldColors() {
        return fieldColors;
    }

    public void setFieldColors(int[] fieldColors) {
        this.fieldColors = fieldColors;
    }

    public int getSelectedFieldColor() {
        return selectedFieldColor;
    }

    public void setSelectedFieldColor(int selectedFieldColor) {
        this.selectedFieldColor = selectedFieldColor;
    }

    public int getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(int hoverColor) {
        this.hoverColor = hoverColor;
    }
    //endregion

}
