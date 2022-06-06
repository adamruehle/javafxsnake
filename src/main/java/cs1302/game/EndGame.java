package cs1302.game;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * An {@code Region} displaying the player's {@code score} when they lose a {@link SnakeGame}
 * that allows the player to start a new {@link SnakeGame}.
 */
public class EndGame extends Region {

    Button playAgain; // button to allow the user to reinitialize the SnakeGame
    Label score; // current score of the player

    /**
     * Constructs a new {@code Region} displaying the player's score when the game
     * ended and containing a {@code Button} allowing the player to start a new
     * game of Snake.
     */
    public EndGame() {
        /* format region */
        this.setMinSize(1280, 720);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        /* create and format play button */
        playAgain = new Button("Play Again");
        playAgain.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 100));
        playAgain.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        playAgain.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        /* create and format score label */
        score = new Label();
        score.setTextAlignment(TextAlignment.CENTER);
        score.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 70));
        /* position play button and score label within Region */
        this.positionInArea(playAgain, 335, 150, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(score, 500, 400, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        // add play button and score label to the EndGame Region
        this.getChildren().addAll(playAgain, score);
    } // EndGame

    /**
     * Updates the score that will be displayed when the player loses.
     *
     * @param score the specified value which the score will be updated to
     */
    public void updateEndGameScore(int score) {
        this.score.setText("Score: " + score);
    } // update

    /**
     * The {@code playAgain} {@code Button} handler which allows {@link SnakeGame}
     * to start a new game of Snake.
     *
     * @param run the specified lambda to be run
     */
    public void onPlayAgain(EventHandler run) {
        playAgain.setOnMouseClicked(run);
    } // onStartAgain

} // EndGame
