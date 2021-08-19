package author.wzn.adapter;

public class Viewer {

    private String mother_tongue;

    private Movie watchingMovie;

    public Viewer(String var1,Movie var2) {
        mother_tongue = var1;
        watchingMovie = var2;
    }

    public void setMother_tongue(String mother_tongue) {
        this.mother_tongue = mother_tongue;
    }

    public String getMother_tongue() {
        return mother_tongue;
    }

    public Movie getWatchingMovie() {
        return watchingMovie;
    }

    public void setWatchingMovie(Movie watchingMovie) {
        this.watchingMovie = watchingMovie;
    }
}
