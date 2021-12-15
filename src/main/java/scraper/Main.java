package scraper;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.newhomesource.com/plan/bruckner-wlh-taylor-morrison-austin-tx/1771466";

//        String url = "localhost";
        CacheScraper cacheScraper = new CacheScraper();

        Home home2 = cacheScraper.parse(url);
        System.out.println(home2);

    }
}