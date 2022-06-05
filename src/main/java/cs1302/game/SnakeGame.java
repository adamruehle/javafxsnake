package cs1302.game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

/**
 * A simple recreation of a classic arcade game, Snake.
 */
public class SnakeGame extends Game {

    private final int width = 45;
    private final int height = 21;
    private int difficulty; // the lower the value, the faster the game will update
    private int step; // used to test if enough time has passed for the game state to update
    private int score; // each time the player's snake consumes an apple, increments by 5
    private final Label scoreLabel; // Label containing the player's current score
    private final Board board; // board containing each Position
    private Snake snake; // snake that the player controls
    private Snake.Direction nextDirection; // next direction for the snake to move
    private Position food; // Position of food
    private final Random rng; // used to generate the Position of new food
    private final StartGame startGame; // displayed when game begins
    private final EndGame endGame; // displayed when the game ends

    /**
     * Constructs a {@code SnakeGame} object. Each {@code SnakeGame} contains
     * {@code Board} where the {@code Snake} and {@code food} will be located,
     * a RNG to randomly place the {@code Food}, a {@code ScoreLabel} to display
     * the player's current score, a {@code StartGame} Region that will be
     * displayed when the game begins, and an {@code EndGame} Region that will
     * be displayed when the player loses.
     *
     * @param width  minimum game region width
     * @param height minimum game region height
     * @param fps    target frames per second (FPS)
     */
    public SnakeGame(int width, int height, int fps) {
        super(width, height, fps); // the overarching game Region
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        rng = new Random(); // used to generate each random fruit Position
        /* create and position the game board */
        board = new Board(this.width, this.height);
        positionInArea(board, 80, 124, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        /* create and position the score label */
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 70));
        positionInArea(scoreLabel, 500, 10, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        startGame = new StartGame();
        initStartGame();
        /* create end game Region and make lambda for a new game */
        endGame = new EndGame();
        endGame.onPlayAgain((e) -> this.playAgain()); // allow user to init a new SnakeGame
        /* display the startGame Region */
        this.getChildren().add(startGame);
    } // SnakeGame

    private void initStartGame() {
        /* allow the StartGame screen to accurately change difficulty or start the game */
        this.startGame.onStart((e) -> this.showBoard());
        this.startGame.onEasy((e) -> {
            difficulty = 17;
            startGame.easySelect();
        });
        this.startGame.onNormal((e) -> {
            difficulty = 12;
            startGame.normalSelect();
        });
        this.startGame.onHard((e) -> {
            difficulty = 7;
            startGame.hardSelect();
        });
    } // initStartGame

    /**
     * Initializes the {@code SnakeGame} by creating a new {@code Snake} with no
     * direction, placing the food, and setting the difficulty & score to 0.
     */
    @Override
    protected void init() {
        snake = new Snake(new Position(width / 2, height / 2));
        nextDirection = Snake.Direction.NONE;
        placeFood();
        step = 0;
        score = 0;
        startGame.easySelect();
        difficulty = 17;
    } // init

    /**
     * Performs one iteration of the main {@code SnakeGame} loop. This method
     * will only execute the body of its code once the step value of this
     * {@code SnakeGame} is greater than the difficulty level.
     */
    @Override
    protected void update() {
        step++; // step increments each time update is called (60 times a second)
        if (step < difficulty) {
            return; // update returns with nothing unless step is greater than difficulty
        } // if
        step = 0; // reset step
        snake.setDirection(nextDirection); // sets snake direction
        if (isLoss()) {
            showEndGame();
        } // if
        /* move the snake and check if it consumed food */
        if (snake.move(food)) {
            placeFood();
            updateScore();
        } // if
        setCells();
    } // update

    /**
     * Sets each cell type.
     */
    private void setCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (food.getX() == x && food.getY() == y) {
                    board.setCellType(x, y, Board.CellType.FOOD);
                } else if (snake.isBody(x, y)) {
                    board.setCellType(x, y, Board.CellType.SNAKE);
                } else {
                    board.setCellType(x, y, Board.CellType.BLANK);
                } // if
            } // for
        } // for
    } // setCells

    /**
     * Sets the {@code nextDirection} of the {@code Snake}.
     *
     * @param event associated key event
     */
    @Override
    protected void handleKeyPressed(KeyEvent event) {
        super.handleKeyPressed(event);
        switch (event.getCode()) {
            case LEFT -> nextDirection = Snake.Direction.LEFT;
            case RIGHT -> nextDirection = Snake.Direction.RIGHT;
            case UP -> nextDirection = Snake.Direction.UP;
            case DOWN -> nextDirection = Snake.Direction.DOWN;
        } // switch
    } // handleKeyPressed

    /**
     * Randomly place food on the {@code Board} on any Position unless
     * the {@code Snake} currently occupies that position.
     */
    public void placeFood() {
        while (true) {
            /* set random x and y values */
            int x = rng.nextInt(width);
            int y = rng.nextInt(height);
            if (!snake.isBody(x, y)) {
                food = new Position(x, y);
                break;
            } // if
        } // while
    } // placeFood

    private void updateScore() {
        switch (difficulty) {
            case 17 -> score += 2;
            case 12 -> score += 5;
            case 7 -> score += 10;
        } // switch
        scoreLabel.setText("Score: " + score);
    } // setScore

    /**
     * Returns true if the player has lost the game; false otherwise. A player
     * has lost the {@code SnakeGame} when the head of the {@code Snake} occupies
     * the same {@code Position} as the border or one of its body
     * {@code Position}s.
     *
     * @return true if the player has lost the game; false otherwise
     */
    private boolean isLoss() {
        for (Position position : snake.getBody()) {
            if (position.equals(snake.getHead()) && snake.getHead() != position) {
                return true;
            } // if
        } // for
        // if
        return snake.getHead().getX() == -1 || snake.getHead().getX() == width ||
                snake.getHead().getY() == -1 || snake.getHead().getY() == height;
    } // isLoss

    private void showBoard() {
        this.getChildren().remove(startGame);
        this.getChildren().addAll(board, scoreLabel);
        play();
    } // showStartGame

    /**
     * Stops the main game loop, removes the {@code board} from the
     * {@code Scene}, and adds the {@code endGame}.
     */
    private void showEndGame() {
        stop();
        this.getChildren().removeAll(board, scoreLabel);
        endGame.update(score);
        this.getChildren().add(endGame);
    } // showEndGame

    /**
     * Removes the {@code endGame} from the {@code Scene}, adds the
     * {@code board}, and unpauses the main game loop.
     */
    private void playAgain() {
        this.getChildren().remove(endGame);
        this.getChildren().add(startGame);
        this.scoreLabel.setText("Score: 0");
        init();
        play();
    } // playAgain

} // SnakeGame
