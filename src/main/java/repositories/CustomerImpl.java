package repositories;

import models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> readAll() {
        return null;
    }

    @Override
    public Customer readByID(Integer integer) {
        return null;
    }

    @Override
    public String readByName(String name) {
        return null;
    }

    @Override
    public Customer page(int limit, int offset) {
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
    public Customer highestSpender() {
        return null;
    }

    @Override
    public String mostPopGenre() {
        return null;
    }
}
