package cs1302.omega;

import cs1302.game.SnakeGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code GameApp} creates and runs a simple version of the classic
 * {@code SnakeGame}. In Snake, the player is forced to grow their snake
 * by eating fruit if they want to achieve a high score. However, if they
 * run into the wall or a piece of their snake's body, they lose.
 */
public class GameApp extends Application {

    /**
     * Constructs an {@code GameApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public GameApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        // snake game
        SnakeGame snakeGame = new SnakeGame(1280, 720, 60);

        // setup scene
        VBox root = new VBox(snakeGame);
        Scene scene = new Scene(root);

        // setup stage
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        // play the game
        snakeGame.play();

    } // start

} // OmegaApp
