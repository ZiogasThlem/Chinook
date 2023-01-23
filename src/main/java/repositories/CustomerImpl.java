package repositories;

import models.Customer;
import models.CustomerGenre;
import models.CustomerSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

@Repository
public class CustomerImpl implements CustomerRepository{
    private final String url;
    private final String username;
    private final String password;

    @Autowired
    public CustomerImpl(@Value("${spring.datasource.url}") String url,
                        @Value("${spring.datasource.username}") String username,
                        @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Customer> readAll() {
        return null;
    }

    @Override
    public Customer readByID(Integer integer) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,integer);
            ResultSet result = statement.executeQuery();
            customer = new Customer(
                    result.getInt("customer_id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("country"),
                    result.getInt("postal_code"),
                    result.getInt("phone"),
                    result.getString("email")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public String readByName(String name) {
        return null;
    }

    @Override
    public List<Customer> page(int limit, int offset) {

        return null;
    }

        @Override
    public int insert(Customer object) {
        return 0;
    }

    @Override
    public int update(Customer object) {

        return 0;
    }

    @Override
    public String countryWithMostCustomers() {
        return null;
    }

    @Override
    public CustomerSpender highestSpender() {

        return null;
    }

    @Override
    public CustomerGenre mostPopGenre() {

        return null;
    }
}
