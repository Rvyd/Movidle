package tr.edu.erciyes8038.movidle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class    MovieController {

    static final HashMap<Integer, Movie> MOVIES = new HashMap<>();


    // read movies from csv file
    public static void reader() {
        String filePath = "movies/imdb_top_250.csv";
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                //System.out.println(line);
                splitter(line);
            }
        } catch (Exception exception) {
            System.out.println("ERROR");
        }
    }

    // split the given lines and insert to hashmap
    public static void splitter(String line) {
        String[] strings = line.split(";");
        MOVIES.put(Integer.parseInt(strings[0]), new Movie(strings));

    }
}