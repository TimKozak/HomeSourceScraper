package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper {
    @Override
    @SneakyThrows
    public Home parse(String url) {
        Home output;
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement statement = connection.createStatement();
        String query = String.format("select count(*) as count from homes where url='%s';", url);
        ResultSet rs = statement.executeQuery(query);
        int count = rs.getInt("count");
        if (count == 0) {
            Scraper scraper = new DefaultScraper();
            Home home = scraper.parse(url);

            int price = home.getPrice();
            double beds = home.getBeds();
            double bathrooms = home.getBathrooms();
            double garages = home.getGarage();
            String add = String.format("insert into homes(url, price, beds, bathes, garages) values ('%s', '%d', '%f', '%f', '%f');", url, price, beds, bathrooms, garages);
            statement.executeUpdate(add);
            output = new Home(price, beds, bathrooms, garages);
            System.out.println("Added to DB");
        } else {
            // return data from DB
            rs =  statement.executeQuery(String.format("select * from homes where url='%s'", url));
            int price = rs.getInt("price");
            double beds = rs.getDouble("beds");
            double bathes = rs.getDouble("bathes");
            double garages = rs.getDouble("garages");
            output = new Home(price, beds, bathes, garages);
            System.out.println("Took from DB");
        }
        System.out.println(count);
        return output;
    }
};