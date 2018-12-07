import processing.core.PApplet;

public class Main extends PApplet {

    //region Member-Variables
    private final boolean fullScreen = true;
    private final int display = 2;
    private final int windowWidth = 700;
    private final int windowHeight = 800;

    private int frameRate = 60;

    private int backgroundColor = color(209);
    private int textColor = color(0);

    private boolean firstTime = true;

    private Grid grid;
    private Dame[] figuresWhite;
    private Dame[] figuresBlack;
    //endregion

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {

        if (fullScreen) {
            fullScreen(display);
        } else {
            size(windowWidth, windowHeight);
        }

    }

    public void setup() {

        background(backgroundColor);
        frameRate(frameRate);

        grid = new Grid(8, 8, 70, 50, new int[]{color(255), color(0)}, color(255, 0, 0), color(200, 0, 0));
        figuresWhite = new Dame[grid.getGridWidth() / 2 * 3];
        figuresBlack = new Dame[figuresWhite.length];

        declareFigures();

    }

    private void declareFigures() {

        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < grid.getGridWidth(); j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        figuresWhite[index] = new SingleDame(j, i, Color.WHITE);
                    } else {
                        figuresBlack[index++] = new SingleDame(j, grid.getGridHeight() - i - 1, Color.BLACK);
                    }
                } else {
                    if (j % 2 != 0) {
                        figuresWhite[index++] = new SingleDame(j, i, Color.WHITE);
                    } else {
                        figuresBlack[index] = new SingleDame(j, grid.getGridHeight() - i - 1, Color.BLACK);
                    }
                }
            }
        }
        
    }

    public void draw() {

        resetColors();

        if (firstTime) {
            declareFields();
            firstTime = false;
        }

        interpretMouseInput();

        printGrid();

        for (int i = 0; i < figuresWhite.length; i++) {
            print(figuresWhite[i]);
            print(figuresBlack[i]);
        }

            /*interpretPressedKey();

            declareGrid();

            markSelectedField(player1);
            markSelectedField(player2);

            drawGrid();

            drawBox();

            checkForCollision(player1, player2);

            drawRobot(player1);
            drawRobot(player2);

            drawText();*/

    }

    private void declareFields() {

        if (width > height) {

            grid.setFieldSize((height - grid.getMarginTopBottom() * 2) / grid.getGridHeight());
            grid.setMarginLeftRight((width - grid.getFieldSize() * grid.getGridWidth()) / 2);

        } else if (width < height) {

            grid.setFieldSize((width - grid.getMarginLeftRight() * 2) / grid.getGridWidth());
            grid.setMarginTopBottom((height - grid.getFieldSize() * grid.getGridHeight()) / 2);

        } else {

            grid.setFieldSize((width - grid.getMarginLeftRight() * 2) / grid.getGridWidth());
        }

    }

    private void resetColors() {

        for (int i = 0; i < grid.colors.length; i++) {
            for (int j = 0; j < grid.colors[i].length; j++) {
                if (grid.colors[i][j] == grid.getHoverColor()) {
                    grid.setField(i, j);
                }
            }
        }

    }

    private void interpretMouseInput() {

        boolean keepRunning = true;

        for (int i = 0; i < grid.colors.length && keepRunning; i++) {
            for (int j = 0; j < grid.colors[i].length; j++) {
                if (grid.colors[i][j] == grid.getSelectedFieldColor()) {


                    keepRunning = false;
                } else {
                    if (mousePressed) {
                        for (int k = 0; k < figuresWhite.length; k++) {
                            colorSelectedField(figuresWhite[k]);
                            colorSelectedField(figuresBlack[k]);
                        }
                    } else {
                        for (int k = 0; k < figuresWhite.length; k++) {
                            colorHoveredField(figuresWhite[k]);
                            colorHoveredField(figuresBlack[k]);
                        }
                    }
                }
            }
        }

                /*for (int j = 0; j < figuresWhite[i].length; j++) {
                    boolean mouseIsOverField = mouseX <= grid.getMarginLeftRight() + i * grid.getGridWidth()
                            && mouseX >= grid.getMarginLeftRight() + (i + 1) * grid.getGridWidth()
                            && mouseY <= grid.getMarginTopBottom() + i * grid.getGridHeight()
                            && mouseY >= grid.getMarginTopBottom() + (i + 1) * grid.getGridHeight();

                    if (mouseIsOverField) {
                        if (mousePressed) {
                            grid.colors[i][j] = grid.getSelectedFieldColor();
                        } else {
                            grid.colors[i][j] = grid.getHoverColor();
                        }
                    }
                }*/



    }

    private void colorSelectedField(Dame figure) {
        if (mouseX > figure.getX() * grid.getFieldSize() + grid.getMarginLeftRight()
                && mouseX < (figure.getX() + 1) * grid.getFieldSize() + grid.getMarginLeftRight()
                && mouseY > figure.getY() * grid.getFieldSize() + grid.getMarginTopBottom()
                && mouseY < (figure.getY() + 1) * grid.getFieldSize() + grid.getMarginTopBottom()) {
            if (grid.colors[figure.getX()][figure.getY()] == grid.getSelectedFieldColor()) {
                grid.setField(figure.getX(), figure.getY());
            } else {
                grid.colors[figure.getX()][figure.getY()] = grid.getSelectedFieldColor();
            }
            delay(150);
        }
    }

    private void colorHoveredField(Dame figure) {
        if (mouseX > figure.getX() * grid.getFieldSize() + grid.getMarginLeftRight()
                && mouseX < (figure.getX() + 1) * grid.getFieldSize() + grid.getMarginLeftRight()
                && mouseY > figure.getY() * grid.getFieldSize() + grid.getMarginTopBottom()
                && mouseY < (figure.getY() + 1) * grid.getFieldSize() + grid.getMarginTopBottom()
                && grid.colors[figure.getX()][figure.getY()] != grid.getSelectedFieldColor()) {
            grid.colors[figure.getX()][figure.getY()] = grid.getHoverColor();
        }
    }

    private void printGrid() {

        for (int i = 0; i < grid.getGridWidth(); i++) {
            for (int j = 0; j < grid.getGridHeight(); j++) {
                fill(grid.colors[i][j]);
                rect(grid.getMarginLeftRight() + i * grid.getFieldSize(),
                        grid.getMarginTopBottom() + j * grid.getFieldSize(),
                        grid.getFieldSize(),
                        grid.getFieldSize());
            }
        }

    }

    private void print(Dame figure) {

        float x = grid.getMarginLeftRight() + figure.getX() * grid.getFieldSize() + grid.getFieldSize() * 0.5f;
        float y = grid.getMarginTopBottom() + figure.getY() * grid.getFieldSize() + grid.getFieldSize() * 0.5f;

        if (figure.getColor() == Color.BLACK) {
            fill(0);
        } else if (figure.getColor() == Color.WHITE){
            fill(255);
        }

        ellipse(x, y, grid.getFieldSize() * 0.8f, grid.getFieldSize() * 0.8f);

        if (figure instanceof DoubleDame) {
            ellipse(x + grid.getFieldSize() * 0.1f, y + grid.getFieldSize() * 0.1f, grid.getGridWidth() * 0.6f, grid.getFieldSize() * 0.6f);
        }

    }

    /*private void checkForCollision(Robot robot1, Robot robot2) {

        boolean isBoxCollision = false;

        if (robot1.getX() == boxX && robot1.getY() == boxY) {

            scorePlayer1++;
            isBoxCollision = true;

        } else if (robot2.getX() == boxX && robot2.getY() == boxY) {

            scorePlayer2++;
            isBoxCollision = true;

        }

        if (Math.max(scorePlayer1, scorePlayer2) > highscore) {
            highscore = Math.max(scorePlayer1, scorePlayer2);
        }

        while (((robot1.getX() == boxX && robot1.getY() == boxY) || (robot2.getX() == boxX && robot2.getY() == boxY)) && isBoxCollision) {

            boxX = (int) (Math.random() * gridWidth - 1);
            boxY = (int) (Math.random() * gridHeight - 1);

        }

        if (player1.getX() == player2.getX() && player1.getY() == player2.getY()) {

            gameOver = true;
            wait = true;

        }

    }*/

    /*private void startScreen() {

        String text = "Move a robot on a grid by pressing [" + (keyStepForwardPlayer1 + "").toUpperCase() + "] or [" + (keyStepForwardPlayer2 + "").toUpperCase() + "] to make a step in the selected direction and [" + (keyRotateLeftPlayer1 + "").toUpperCase() + "] or [" + (keyRotateLeftPlayer2 + "").toUpperCase() + "] to rotate left. The goal is to collect more boxes than your opponent in 60 seconds.";

        background(startScreenBackgroundColor);

        fill(startScreenTextcolor);

        textSize(Math.min(width, height) / 10);
        textAlign(CENTER, BOTTOM);
        text("ROBOT", width / 2, height / 3);

        textSize(Math.min(width, height) / 70);
        textAlign(RIGHT, TOP);
        text("Sebastian Scholl", width - 10, 10);

        textSize(Math.min(width, height) / 50);
        textAlign(CENTER, TOP);
        text(text, width / 3, height / 2, width / 3, height);

        textSize(Math.min(width, height) / 40);
        textAlign(CENTER, BOTTOM);
        text("Press any key to start", width / 2, height - height / 10);

        if (keyPressed || mousePressed) {

            startScreen = false;
            background(backgroundColor);

        }

    }

    private void gameOver() {

        background(gameOverBackgoundColor);

        fill(gameOverTextColor);

        textSize(Math.min(marginLeftRight, marginTopBottom) / 2);

        textAlign(CENTER, BOTTOM);
        if (scorePlayer1 > scorePlayer2) {
            text("Player 1 won!", width / 2, height / 2);
        } else if (scorePlayer1 < scorePlayer2) {
            text("Player 2 won!", width / 2, height / 2);
        } else {
            text("Nobody won!", width / 2, height / 2);
        }

        textAlign(CENTER, TOP);
        text("Press any key to PLAY AGAIN", width / 2, height / 2);

        textSize(Math.min(marginLeftRight, marginTopBottom) / 4);
        textAlign(LEFT, BOTTOM);
        text("Score: P1 [" + scorePlayer1 + "], P2 [" + scorePlayer2 + "]", marginLeftRight / 10, marginTopBottom / 2);

        textAlign(RIGHT, BOTTOM);
        text("Highscore: " + highscore, width - marginLeftRight / 10, marginTopBottom / 2);

        if ((keyPressed || mousePressed) && !wait) {

            gameOver = false;
            background(backgroundColor);
            declareMemberVariables();

        }

    }*/

    /*private void drawText() {

        noStroke();
        fill(backgroundColor);
        rect(0, 0, width, marginTopBottom);

        stroke(0);

        String textStepForward = "Step forward [" + (keyStepForwardPlayer1 + "").toUpperCase() + "] [" + (keyStepForwardPlayer2 + "").toUpperCase() + "]";
        String textRotateLeft = "Rotate Left [" + (keyRotateLeftPlayer1 + "").toUpperCase() + "] [" + (keyRotateLeftPlayer2 + "").toUpperCase() + "]";
        int y = marginTopBottom / 2;

        fill(textColor);
        textSize(Math.min(marginLeftRight, marginTopBottom) / 4);

        textAlign(LEFT, BOTTOM);
        text(textStepForward, marginLeftRight / 10, y);

        textAlign(LEFT, TOP);
        text(textRotateLeft, marginLeftRight / 10, y);

        textAlign(CENTER, BOTTOM);
        text("Exit [ESC]", width / 2, y);

        textAlign(CENTER, TOP);
        text(time + "s", width / 2, y);

        textAlign(RIGHT, BOTTOM);
        text("Score: P1 [" + scorePlayer1 + "], P2 [" + scorePlayer2 + "]", width - marginLeftRight / 10, y);

        textAlign(RIGHT, TOP);
        text("Highscore: " + highscore, width - marginLeftRight / 10, y);

    }*/

    /*private void markSelectedField(Robot robot) {

        int x = robot.getX();
        int y = robot.getY();

        switch (robot.getDirection()) {

            case NORTH:
                y -= 1;
                break;

            case EAST:
                x += 1;
                break;

            case SOUTH:
                y += 1;
                break;

            case WEST:
                x -= 1;
                break;

        }

        if (x < 0 && robot.getTeleportMode()) {
            x = gridWidth - 1;
        } else if (x >= gridWidth && robot.getTeleportMode()) {
            x = 0;
        } else if (x < 0 || x >= gridWidth) {
            x = -1;
        }

        if (y < 0 && robot.getTeleportMode()) {
            y = gridHeight - 1;
        } else if (y >= gridHeight && robot.getTeleportMode()) {
            y = 0;
        } else if (y < 0 || y >= gridHeight) {
            y = -1;
        }

        if (y != -1 && x != -1) {
            grid[x][y] = selectedFieldColor;
        }

    }

    private void declareGrid() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = gridColor;
            }
        }

    }

    private void drawGrid() {

        for (int i = 0; i < gridWidth; i++) {

            for (int j = 0; j < gridHeight; j++) {

                int x = marginLeftRight + fieldWith * i;
                int y = marginTopBottom + fieldHeight * j;

                fill(grid[i][j]);
                rect(x, y, fieldWith, fieldHeight);

            }

        }

    }

    private void drawRobot(Robot robot) {

        int radius = Math.min(fieldWith, fieldHeight) - Math.min(fieldWith, fieldHeight) / 10;
        int x = marginLeftRight + robot.getX() * fieldWith + fieldWith / 2;
        int y = marginTopBottom + robot.getY() * fieldHeight + fieldHeight / 2;

        fill(robotColor);
        ellipse(x, y, radius, radius);

        fill(textColor);
        textAlign(CENTER, CENTER);
        if (robot.getTeleportMode()) {
            text("T", x, y);
        } else {
            text("R", x, y);
        }

    }

    private void interpretPressedKey() {

        if (keyRotateLeftPlayer1 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player1.rotateLeft();
        } else if (keyStepForwardPlayer1 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player1.stepForward();
        } else if (keyChangeModePlayer1 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player1.setTeleportMode(!player1.getTeleportMode());
            player2.setTeleportMode(player1.getTeleportMode());
        }

        if (keyRotateLeftPlayer2 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player2.rotateLeft();
        } else if (keyStepForwardPlayer2 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player2.stepForward();
        } else if (keyChangeModePlayer2 == (key + "").toLowerCase().charAt(0) && keyPressed) {
            player2.setTeleportMode(!player2.getTeleportMode());
            player1.setTeleportMode(player2.getTeleportMode());
        }

        key = '§';

    }*/

}