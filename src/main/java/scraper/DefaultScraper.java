package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper {
    private final static String PRICE_SELECTOR = ".detail__info-xlrg";
    private final static String BED_SELECTOR = ".nhs_BedsInfo span";
    private final static String BATH_SELECTOR = ".nhs_BathsInfo span";
    private final static String GARAGE_SELECTOR = ".nhs_GarageInfo span";

    @Override @SneakyThrows
    public Home parse(String url) {
        Document doc = Jsoup.connect(url).get();

        int price = getPrice(doc);
        double beds = getBeds(doc);
        double bathrooms = getBaths(doc);
        double garages = getGarages(doc);

        return new Home(price, beds, bathrooms, garages);
    }

    private static int getPrice(Document doc) {
        String priceText = doc.selectFirst(PRICE_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    private static double getBeds(Document doc) {
        String bedText = doc.selectFirst(BED_SELECTOR).text();
        return Double.parseDouble(bedText);
    }

    private static double getBaths(Document doc) {
        String bathText = doc.selectFirst(BATH_SELECTOR).text();
        return Double.parseDouble(bathText);
    }

    private static double getGarages(Document doc) {
        String garageText = doc.selectFirst(GARAGE_SELECTOR).text();
        return Double.parseDouble(garageText);
    }
}
