package author.wzn.adapter;

public interface Adapter {

    boolean support(String var);

    void handle(Movie movie);

}
