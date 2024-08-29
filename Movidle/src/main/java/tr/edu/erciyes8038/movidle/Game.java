package tr.edu.erciyes8038.movidle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Game {

    Movie randomMovie;

    //generate random movie
    public void randomMovie() {
        Random random = new Random();
        int key = random.nextInt(1, 250);
        randomMovie = MovieController.MOVIES.get(key);

    }

    public Movie tempMovie = null;

    // compare to guessed movie and the random movie
    public boolean[] gameControl(String gamerMovie) {

        boolean flag = true;
        boolean[] labelBooleans = new boolean[6];

        Iterator<Integer> iterator = MovieController.MOVIES.keySet().iterator();
        while (iterator.hasNext() && flag) {
            tempMovie = MovieController.MOVIES.get(iterator.next());
            String s = tempMovie.title;
            if (gamerMovie.equalsIgnoreCase(s)) {
                flag = false;

            } else tempMovie = null;

            if (tempMovie != null && randomMovie.title.equalsIgnoreCase(tempMovie.title))
                Arrays.fill(labelBooleans, true);


        }
        if (!flag ) {
            if (randomMovie.getYear().equalsIgnoreCase(tempMovie.getYear()))
                labelBooleans[1] = true;
            if (randomMovie.getGenre().equalsIgnoreCase(tempMovie.getGenre()))
                labelBooleans[2] = true;
            if (randomMovie.getOrigin().equalsIgnoreCase(tempMovie.getOrigin()))
                labelBooleans[3] = true;
            if (randomMovie.getDirector().equalsIgnoreCase(tempMovie.getDirector()))
                labelBooleans[4] = true;
            if (randomMovie.getStar().equalsIgnoreCase(tempMovie.getStar()))
                labelBooleans[5] = true;

        } else {
            //for warning screen
            Label warning = new Label("The Movie not available \n in the top IMDB 250 ");
            Scene scene3 = new Scene(warning, 350, 200);
            warning.setAlignment(Pos.CENTER);
            warning.setFont(Font.font(30));
            warning.setStyle("-fx-border-color:red");
            warning.setTextFill(Color.RED);
            Stage stage3 = new Stage();
            stage3.setScene(scene3);
            stage3.show();

        }
        //indicates the color of label
        //true for green and false for red
        return labelBooleans;

    }

}