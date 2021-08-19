package author.wzn.adapter;

public class ToEnglishAdapter implements Adapter{

    @Override
    public boolean support(String usingLanguage) {
        return "English".equals(usingLanguage);
    }

    @Override
    public void handle(Movie movie) {
        movie.display();
        System.out.println("...English context ...");
    }
}
