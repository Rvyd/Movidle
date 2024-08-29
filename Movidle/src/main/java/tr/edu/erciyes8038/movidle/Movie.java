package tr.edu.erciyes8038.movidle;

public class Movie {

    private String no, year, genre, origin, director, star, imdbLink;
    public String title;

    public Movie(String title) {
        this.title = title;
    }

    // Create movie object through given split string arrays
    public Movie(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            switch (i) {
                case 0 -> this.no = strings[0];
                case 1 -> this.title = strings[1];
                case 2 -> this.year = strings[2];
                case 3 -> this.genre = strings[3];
                case 4 -> this.origin = strings[4];
                case 5 -> this.director = strings[5];
                case 6 -> this.star = strings[6];
                case 7 -> this.imdbLink = strings[7];


            }

        }
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDirector() {
        return director;
    }

    public String getStar() {
        return star;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "no='" + no + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", origin='" + origin + '\'' +
                ", director='" + director + '\'' +
                ", star='" + star + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                '}';
    }


}