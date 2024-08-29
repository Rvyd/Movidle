package tr.edu.erciyes8038.movidle;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovieApplication extends Application {
    VBox root = new VBox();
    int flag = 5;
    Game game = new Game();
    final Image heart = new Image("file:image/heart.png");

    //for the guess movie
    TextField textField = new TextField();

    @Override
    public void start(Stage stage) {

        MovieController.reader();

        game.randomMovie();

        textField.setPrefSize(600.0, 2);
        //style of the background image
        Image cameraImage = new Image("file:image/camera.png");
        BackgroundImage camera = new BackgroundImage(cameraImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        Background cameraBack = new Background(camera);
        root.setBackground(cameraBack);

        HBox heartsImage = new HBox();
        ImageView[] heartArray = {new ImageView(), new ImageView(), new ImageView(), new ImageView(), new ImageView()};
        for (ImageView image : heartArray) {
            imageStyle(image);
        }

        heartsImage.getChildren().addAll(heartArray);

        Button guessBtn = new Button("Guess");
        Button deleteBtn = new Button("Delete");
        Button closeBtn = new Button("Close");

        closeBtn.setOnAction(e -> stage.close());

        deleteBtn.setOnAction(e -> textField.clear());

        guessBtn.setOnAction(e -> {
            flagCounter();
            if (flag >= 0) {
                animationHeart(heartArray);
            // set the labels color
                boolean[] booleans = game.gameControl(textField.getText());
                if (game.tempMovie != null) {
                    Label titleLabel = new Label();
                    setLabelStyle(titleLabel);

                    Label yearLabel = new Label();
                    setLabelStyle(yearLabel);

                    Label genreLabel = new Label();
                    setLabelStyle(genreLabel);

                    Label originLabel = new Label();
                    setLabelStyle(originLabel);

                    Label directorLabel = new Label();
                    setLabelStyle(directorLabel);

                    Label starLabel = new Label();
                    setLabelStyle(starLabel);

                    Label[] labels = {titleLabel, yearLabel, genreLabel, originLabel, directorLabel, starLabel};

                    movieAnimation(labels, booleans, game.tempMovie, new HBox(), heartArray);


                }
            } else {
                guessBtn.setDisable(true);
                //warning screen
                Label message2 = new Label("The Count of The guess\n is over.!!!");
                // name of the guessed movie
                Label movieName = new Label("\n" + "~" + game.randomMovie.title + "~");
                message2.setAlignment(Pos.CENTER);
                message2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                message2.setTextFill(Color.RED);
                movieName.setTextFill(Color.BLUE);
                movieName.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                VBox warningRoot = new VBox();
                warningRoot.setAlignment(Pos.CENTER);
                warningRoot.getChildren().addAll(message2, movieName);
                scene2 = new Scene(warningRoot, 400, 200);
                Stage stage1 = new Stage();
                stage1.setScene(scene2);
                stage1.setTitle("Message");
                stage1.show();
            }

        });


        root3.setSpacing(10);
        root3.getChildren().addAll(textField, guessBtn, deleteBtn, closeBtn, heartsImage);

        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(150, 150, 150, 150));
        root.getChildren().add(root3);


        stage.setScene(new Scene(root, 1250, 750));
        stage.setTitle("Moviedle");
        stage.show();

    }

    HBox root3 = new HBox();
    Scene scene2;
//animation for the labels
    public void movieAnimation(Label[] labels, boolean[] booleans, Movie movie, HBox hBox, ImageView[] heartArray) {

        boolean flag = true;
        for (int i = 0; i < labels.length; i++) {

            switch (i) {
                case 0 -> labels[i].setText(movie.title);
                case 1 -> labels[i].setText(movie.getYear());
                case 2 -> labels[i].setText(movie.getGenre());
                case 3 -> labels[i].setText(movie.getOrigin());
                case 4 -> labels[i].setText(movie.getDirector());
                case 5 -> labels[i].setText(movie.getStar());
            }

            boolean color = booleans[i];
            labels[i].setStyle((color ? "-fx-background-color:green" : "-fx-background-color:red"));

            FadeTransition ft = new FadeTransition(Duration.millis(1000), labels[i]);
            ft.setFromValue(1.0);
            ft.setToValue(0.1);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();
            hBox.getChildren().add(labels[i]);
            for (boolean b : booleans) {
                if (!b) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            VBox restartRoot = new VBox();
            Label message = new Label("You Won!!!");
            message.setFont(Font.font(30));
            message.setStyle("-fx-border-color:green");
            message.setTextFill(Color.GREEN);

            Button replayButton = new Button("Restart");
            Stage stage1 = new Stage();
            replayButton.setOnAction(e -> {
                try {
                    stage1.close();
                    flagReset();

                    root.getChildren().removeIf(node -> !(node == root3));
                    game.randomMovie();
                    resetHeart(heartArray);
                } catch (Exception exception) {
                    System.out.println("Error");
                }
            });
            restartRoot.getChildren().addAll(message, replayButton);
            restartRoot.setAlignment(Pos.CENTER);
            scene2 = new Scene(restartRoot, 300, 200);

            stage1.setScene(scene2);
            stage1.setTitle("Message");
            stage1.show();

        }

        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setSpacing(10);

        root.getChildren().add(hBox);
    }

    private void setLabelStyle(Label label) {
        label.setStyle("-fx-border-color:red;-fx-background-color:blue; ");
        label.setPadding(new Insets(15, 15, 15, 15));
    }

    public void animationHeart(ImageView[] h) {
        int i = flag;
        FadeTransition ft = new FadeTransition(Duration.millis(1000), h[i]);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.play();
        i--;
    }
//when the game restarted, heart reappear
    public void resetHeart(ImageView[] h) {
        for (int i = 0; i < 5; i++) {
            FadeTransition ft = new FadeTransition(Duration.millis(1000), h[i]);
            ft.setFromValue(0.1);
            ft.setToValue(1.0);
            ft.play();
        }
    }
//reduce the flag counter
    public void flagCounter() {
        flag--;
    }

    //when the game restarted
    public void flagReset() {
        flag = 5;
    }
    //style of the hearts
    private void imageStyle(ImageView imageView) {
        imageView.setImage(heart);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
    }

    public static void main(String[] args) {
        launch();
    }


}