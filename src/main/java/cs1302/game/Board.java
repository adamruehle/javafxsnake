package cs1302.game;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

/**
 * The {@code Board} class is a VBox containing a {@code TilePane}
 * with a number of Rectangles equal to the {@code SnakeGame}'s width x height.
 * For the {@code SnakeGame}, the {@code Board} is where the player will
 * be able to see the {@code Snake}, empty {@code Rectangle}s, and food.
 */
public class Board extends VBox {

    /**
     * An enumeration for {@code CellType}, which can either be blank, food,
     * or part of the snake.
     */
    enum CellType {
        BLANK,
        FOOD,
        SNAKE,
    } // CellType enum

    private final Rectangle[][] board; // board containing the Rectangles

    /**
     * Constructs a {@code Board} with a specified {@code width} and {@code height}.
     * Each {@code Board} contains a {@code Rectangle} 2D array and a
     * {@code playableGrid} where the {@code Snake} will be controlled by the player.
     *
     * @param width the specified width of the {@code Board}
     * @param height the specified height of the {@code Board}
     */
    public Board(int width, int height) {
        // grid to display each Rectangle
        TilePane playableGrid = new TilePane(Orientation.VERTICAL);
        playableGrid.setPrefRows(height);
        this.setBackground(Background.EMPTY);
        // initialize the board of Rectangles
        board = new Rectangle[width][height];
        // places each Rectangle in the board and playableGrid
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = new Rectangle(24, 24);
                playableGrid.getChildren().add(board[x][y]);
            } // for
        } // for
        this.getChildren().add(playableGrid); // add the playableGrid in the VBox
    } // Constructor

    /**
     * Sets the CellType of the Rectangle located at the specified
     * x and y values.
     *
     * @param x the specified x value
     * @param y the specified y value
     * @param cellType the specified {@code CellType}
     */
    public void setCellType(int x, int y, CellType cellType) {
        switch (cellType) {
            case BLANK -> board[x][y].setFill(Color.AZURE);
            case SNAKE -> board[x][y].setFill(Color.GREENYELLOW);
            case FOOD -> board[x][y].setFill(Color.RED);
        } // switch
    } // setCellType

} // Board
