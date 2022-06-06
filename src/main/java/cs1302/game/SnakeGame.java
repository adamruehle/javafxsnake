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
 * A simple recreation of the classic arcade game, Snake. The {@code SnakeGame} employs
 * the related Classes {@link StartGame} allowing the player to start the game,
 * {@link Board} displaying the playable game's visual components, {@link Position}
 * representing the playable game's logic for each visual {@code Rectangle},
 * {@link Snake} representing the playable game's logic for the Snake, and {@link EndGame}
 * allowing the player to start a new game of Snake.
 *
 * It should be noted that {@code SnakeGame} inherits {@link Game}, which is a class I
 * did not write.
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
    private boolean isPaused;
    private Position food; // Position of food
    private final Random rng = new Random(); // used to generate the Position of new food
    private final StartGame startGame; // displayed when game begins
    private boolean startGameShown;
    private final EndGame endGame; // displayed when the game ends
    private boolean endGameShown;

    /**
     * Constructs a {@code SnakeGame} object. Each {@code SnakeGame} contains a {@code Board}
     * where the {@code Snake} and {@code food} will be located, an RNG to randomly place the
     * {@code Food}, a {@code ScoreLabel} to display the player's current score, a {@code StartGame}
     * {@code Region} that will be displayed when the game begins, and an {@code EndGame}
     * {@code Region} that will be displayed when the player loses.
     *
     * @param width  minimum game region pixel width
     * @param height minimum game region pixel height
     * @param fps    target frames per second (FPS)
     */
    public SnakeGame(int width, int height, int fps) {
        super(width, height, fps); // construct parent Game Region
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        /* create and position the game board */
        board = new Board(this.width, this.height);
        this.positionInArea(board, 80, 124, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        /* create and position the score label */
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 70));
        this.positionInArea(scoreLabel, 500, 10, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        /* create the starting game screen and enable its game controls */
        startGame = new StartGame();
        startGameShown = true;
        enableStartGame();
        /* create end game screen and allow it to start a new game */
        endGame = new EndGame();
        endGame.onPlayAgain((e) -> this.playAgain()); // allow user to init a new SnakeGame
        /* display the starting game screen first */
        this.getChildren().add(startGame);
    } // SnakeGame

    /**
     * Allows this {@code SnakeGame} to display the {@code board} when the player decides to
     * begin the Snake game and allows the player to choose a {@code difficulty}.
     */
    private void enableStartGame() {
        /* allow the StartGame screen to accurately change difficulty or start the game */
        this.startGame.onStart((e) -> this.showBoard());
        this.startGame.onEasy((e) -> {
            difficulty = 15;
            startGame.easySelect();
        });
        this.startGame.onNormal((e) -> {
            difficulty = 10;
            startGame.normalSelect();
        });
        this.startGame.onHard((e) -> {
            difficulty = 5;
            startGame.hardSelect();
        });
    } // initStartGame

    /**
     * Removes the {@code startGame} from this {@code SnakeGame} {@code Region} and
     * adds the {@code board} and {@code scoreLabel}.
     */
    private void showBoard() {
        this.getChildren().remove(startGame);
        this.getChildren().addAll(board, scoreLabel);
        startGameShown = false;
        play();
    } // showStartGame

    /**
     * Initializes the {@code SnakeGame} by creating a new {@code Snake} with no direction,
     * placing the food, and setting the difficulty & score to 0.
     */
    @Override
    protected void init() {
        snake = new Snake(new Position(width / 2, height / 2));
        nextDirection = Snake.Direction.NONE;
        placeFood();
        step = 0;
        score = 0;
    } // init

    /**
     * Sets the {@code nextDirection} of the {@code Snake}. Also, allows the player
     * to pause or unpause the main game loop.
     *
     * @param event associated key event
     */
    @Override
    protected void handleKeyPressed(KeyEvent event) {
        super.handleKeyPressed(event);
        switch (event.getCode()) {
            case P:
                if (startGameShown || endGameShown) {
                    break;
                }
                if (!isPaused) {
                    isPaused = true;
                    this.pause();
                } else {
                    isPaused = false;
                    this.play();
                }
                break;
            case LEFT:
                if (!isPaused) {
                    nextDirection = Snake.Direction.LEFT;
                }
                break;
            case RIGHT:
                if (!isPaused) {
                    nextDirection = Snake.Direction.RIGHT;
                }
                break;
            case UP:
                if (!isPaused) {
                    nextDirection = Snake.Direction.UP;
                }
                break;
            case DOWN:
                if (!isPaused) {
                    nextDirection = Snake.Direction.DOWN;
                }
                break;
        } // switch
    } // handleKeyPressed



    /**
     * Performs one iteration of the main {@code SnakeGame} loop. This method will only execute
     * the main body of its code once the {@code step} value of this {@code SnakeGame} is greater
     * than the {@code difficulty} value. In the body {@code update}, the direction of the
     * {@code snake} is set, all game losing cases are checked for and dealt with accordingly (if the
     * player loses then the {@code endGame} {@code Region} is displayed), the {@code snake} is moved
     * while {@code food} and {@code score} are updated, and the visual {@code board} is updated.
     */
    @Override
    protected void update() {
        step++; // step increments each time update is called (60 times a second)
        if (step < difficulty) {
            return; // update does nothing unless step is greater than difficulty
        } // if
        step = 0; // reset step
        snake.setDirection(nextDirection);
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

    /**
     * Stops the main game loop, removes the {@code board} from the
     * {@code Scene}, and adds the {@code endGame}.
     */
    private void showEndGame() {
        stop();
        this.getChildren().removeAll(board, scoreLabel);
        endGame.updateEndGameScore(score);
        this.getChildren().add(endGame);
        endGameShown = true;
    } // showEndGame

    /**
     * Removes the {@code endGame} from the {@code Scene}, adds the
     * {@code board}, and unpauses the main game loop.
     */
    private void playAgain() {
        this.getChildren().remove(endGame);
        this.getChildren().add(startGame);
        this.scoreLabel.setText("Score: 0");
        endGameShown = false;
        startGameShown = true;
        init();
        play();
    } // playAgain

    /**
     * Randomly places food on the {@code board} at any position unless the {@code snake}
     * currently occupies that position.
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

    /**
     * Updates the {@code score} of this {@code SnakeGame} given this {@code difficulty}
     * value.
     */
    private void updateScore() {
        switch (difficulty) {
            case 15 -> score += 2;
            case 10 -> score += 5;
            case 5 -> score += 10;
        } // switch
        scoreLabel.setText("Score: " + score);
    } // setScore

    /**
     * Sets each {@code board} cell type. The cell types will be used to correctly display
     * the {@code board} to the player.
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

} // SnakeGame
