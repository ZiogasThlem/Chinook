package repositories;

import models.Customer;
import models.CustomerCountry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerImpl implements CustomerRepository{
    private final String url;
    private final String username;
    private final String password;

    public CustomerImpl(
                    @Value("${spring.datasource.url}") String url,
                    @Value("${spring.datasource.username}") String username,
                    @Value("${spring.datasource.password}") String password){

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

        String sql = "SELECT  " +
                     "id, " +
                     "firstName, " +
                     "lastName, " +
                     "country, " +
                     "postalCode, " +
                     "phoneNumber, " +
                     "email " +
                     "FROM customer";

        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("country"),
                        result.getInt("postalCode"),
                        result.getInt("phoneNumber"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer readByID(Integer integer) {
        return null;
    }

    @Override
    public Customer readByName(String name) {

        String sql = "SELECT * FROM customer WHERE firstName = LIKE %?%";
        Customer customer = null;

        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            while(result.next()){
                customer = new Customer(

                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("country"),
                        result.getInt("postalCode"),
                        result.getInt("phoneNumber"),
                        result.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;

    }

    @Override
    public Customer page(int limit, int offset) {
        return null;
    }

    @Override
    public int insert(Customer customer) {

        String sql = """
                    INSERT INTO customer\s
                    first_name,
                    last_name,
                    tcountry,
                    postal_code,
                    phone_number,
                    email)
                    VALUES (?,?,?,?,?,?)""";

        int result = 0;

        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,customer.firstName());
            statement.setString(2,customer.lastName());
            statement.setString(3,customer.country());
            statement.setInt(4, customer.postalCode());
            statement.setInt(5, customer.phoneNumber());
            statement.setString(6,customer.email());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;


    }

    @Override
    public int update(Customer object) {
        return 0;
    }

    @Override
    public CustomerCountry countryWithMostCustomers() {

        String sql = """
                SELECT country
                FROM customer
                GROUP BY country
                ORDER BY count(country) DESC
                LIMIT 1;""";

        CustomerCountry country = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            country = new CustomerCountry(result.getString("country"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;

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
