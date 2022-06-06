package cs1302.game;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * {@code StartGame} is a {@code Region} that allows the player to select an {@code easy},
 * {@code normal}, or {@code hard} difficulty and start a new game of Snake.
 */
public class StartGame extends Region {

    Label snakeLabel;
    Button start;
    Button easy;
    Button normal;
    Button hard;

    /**
     * A {@code StartGame} contains a {@code Label} displaying "Snake!" and four {@code Button} allowing
     * the player to select a difficulty and start a new game of Snake.
     */
    public StartGame() {
        /* resize and set background of this Region */
        this.setMinSize(1280, 720);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        /* create and format a Label to display the title of the game */
        snakeLabel = new Label("Snake!");
        snakeLabel.setFont(Font.font("Lucida Sans Unicode", FontWeight.EXTRA_BOLD, 160));
        snakeLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        /* create and format a Button allowing the player to start a new game of Snake */
        start = new Button("Start");
        start.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 100));
        start.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        start.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(2))));
        /* create and format the three difficulty Buttons*/
        easy = new Button("Easy");
        normal = new Button("Normal");
        hard = new Button("Hard");
        formatDifficultyButton(easy);
        formatDifficultyButton(normal);
        formatDifficultyButton(hard);
        /* position and add the Label and four Buttons to this Region */
        this.positionInArea(snakeLabel, 390, 30, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(start, 470, 280, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(easy, 410, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(normal, 570, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(hard, 770, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.getChildren().addAll(snakeLabel, start, easy, normal, hard);
    } // StartGame

    /**
     * Formats the specified {@code button}.
     *
     * @param button the specified {@code Button} to be formatted
     */
    private void formatDifficultyButton(Button button) {
        button.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 30));
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    } // formatDifficultyButton

    /**
     * Handles when the player clicks the {@code start} {@code Button}.
     *
     * @param run the code to be run when the player clicks the {@code start} {@code Button}
     */
    public void onStart(EventHandler<MouseEvent> run) {
        start.setOnMouseClicked(run);
    } // onStartAgain

    /**
     * Handles when the player clicks the {@code easy} {@code Button}.
     *
     * @param run the code to be run when the player clicks the {@code easy} {@code Button}
     */
    public void onEasy(EventHandler<MouseEvent> run) {
        easy.setOnMouseClicked(run);
    } // onEasy

    /**
     * Handles when the player clicks the {@code normal} {@code Button}.
     *
     * @param run the code to be run when the player clicks the {@code normal} {@code Button}
     */
    public void onNormal(EventHandler<MouseEvent> run) {
        normal.setOnMouseClicked(run);
    } // onEasy

    /**
     * Handles when the player clicks the {@code hard} {@code Button}.
     *
     * @param run the code to be run when the player clicks the {@code hard} {@code Button}
     */
    public void onHard(EventHandler<MouseEvent> run) {
        hard.setOnMouseClicked(run);
    } // onEasy

    /**
     * Visually shows the player that they have clicked/selected the {@code easy} {@code Button}.
     */
    public void easySelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // easySelected

    /**
     * Visually shows the player that they have clicked/selected the {@code normal} {@code Button}.
     */
    public void normalSelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // normalSelect

    /**
     * Visually shows the player that they have clicked/selected the {@code hard} {@code Button}.
     */
    public void hardSelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // hardSelect

} // StartGame
