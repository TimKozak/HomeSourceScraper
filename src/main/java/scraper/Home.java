package scraper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Home {
    private int price;
    private double beds;
    private double bathrooms;
    private double garage;

    @Override
    public String toString() {
        return "Home{" +
                "price=" + price +
                ", beds=" + beds +
                ", bathrooms=" + bathrooms +
                ", garage=" + garage +
                '}';
    }
}
