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

/**
 * {@code StartGame}
 */
public class StartGame extends Region {

    Label snakeLabel;
    Button start;
    Button easy;
    Button normal;
    Button hard;

    public StartGame() {
        this.setMinSize(1280, 720);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));

        snakeLabel = new Label("Snake!");
        snakeLabel.setFont(Font.font("Lucida Sans Unicode", FontWeight.EXTRA_BOLD, 160));
        snakeLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("mediumslateblue"),
                CornerRadii.EMPTY, Insets.EMPTY)));

        start = new Button("Start");
        start.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 100));
        start.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        start.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        easy = new Button("Easy");
        formatDifficultyButton(easy);

        normal = new Button("Normal");
        formatDifficultyButton(normal);

        hard = new Button("Hard");
        formatDifficultyButton(hard);

        this.positionInArea(snakeLabel, 390, 30, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(start, 470, 280, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(easy, 410, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(normal, 570, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);
        this.positionInArea(hard, 770, 560, 1280, 720, 0, HPos.LEFT, VPos.TOP);

        this.getChildren().addAll(snakeLabel, start, easy, normal, hard);
    } // StartGame

    private void formatDifficultyButton(Button button) {
        button.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 30));
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    } // formatDifficultyButton

    public void onStart(EventHandler run) {
        start.setOnMouseClicked(run);
    } // onStartAgain

    public void onEasy(EventHandler run) {
        easy.setOnMouseClicked(run);
    } // onEasy

    public void onNormal(EventHandler run) {
        normal.setOnMouseClicked(run);
    } // onEasy

    public void onHard(EventHandler run) {
        hard.setOnMouseClicked(run);
    } // onEasy

    public void easySelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // easySelected

    public void normalSelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // normalSelect

    public void hardSelect() {
        easy.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        normal.setBackground(new Background(new BackgroundFill(Paint.valueOf("azure"),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hard.setBackground(new Background(new BackgroundFill(Paint.valueOf("grey"),
                CornerRadii.EMPTY, Insets.EMPTY)));
    } // hardSelect

} // StartGame
