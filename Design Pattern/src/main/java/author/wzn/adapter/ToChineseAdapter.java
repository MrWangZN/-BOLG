package author.wzn.adapter;

public class ToChineseAdapter implements Adapter{

    @Override
    public boolean support(String usingLanguage) {
        return "Chinese".equals(usingLanguage);
    }

    @Override
    public void handle(Movie movie) {
        movie.display();
        System.out.println("...Chinese context ...");
    }
}
