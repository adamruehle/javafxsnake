package cs1302.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Example extends Application {

    public static void main(String[] args) {
        Application.launch(Example.class, args);
    }

    public Example() {}

    @Override
    public void start(Stage stage) {
        /** Setup TilePane where game would be played */
        FlowPane playableGrid = new FlowPane(Orientation.VERTICAL, 0, 0);
        playableGrid.setPrefWrapLength(25);
        playableGrid.setMinSize(500, 500);
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                // The playableGrid consists of 25 rows of Rectangles
                Rectangle rectangle = new Rectangle(20, 20);
                rectangle.setFill(Color.CADETBLUE);
                playableGrid.getChildren().add(rectangle);
            } // for
        } // for
        /** In my game, a Region with a specific background holds the playableGrid */
        VBox root = new VBox();
        root.setMinSize(500, 500);
        root.setBackground(new Background(new BackgroundFill(Color.AZURE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(playableGrid);
        /** Setup scene */
        Scene scene = new Scene(root);
        /** Setup stage */
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();
    }

}
