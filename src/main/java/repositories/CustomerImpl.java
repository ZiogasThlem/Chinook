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
        String sql = "SELECT * FROM customer WHERE customer_id = ?"; //passing the sql query into a string value
        Customer customer = null; //creating a new customer reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){ //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setInt(1,integer); //replacing the question mark with the integer
            ResultSet result = statement.executeQuery(); //making a result set and executing the query
            customer = new Customer( //making a new customer
                    result.getInt("customer_id"),   //adding the customers' id from databases "customer_id" field
                    result.getString("first_name"), //adding the customers' name from databases "first_name" field
                    result.getString("last_name"),  //adding the customers' surname from databases "last_name" field
                    result.getString("country"),    //adding the customers' country from databases "country" field
                    result.getInt("postal_code"),   //adding the customers' postal code from databases "postal_code" field
                    result.getInt("phone"),         //adding the customers' phone from databases "phone" field
                    result.getString("email")      //adding the customers' email from databases "email" field
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage()); //prints the exceptions' message
        }
        return customer; //returns the customer
    }

    @Override
    public String readByName(String name) {
        return null;
    }

    @Override
    public List<Customer> page(int limit, int offset) {
        String sql = "SELECT first_name, last_name, phone, email FROM customer LIMIT ? OFFSET ?"; //passing the sql query into a string value
        List<Customer> customers = null; //creating a new list of customers
        Customer customer = null; //creating a new customer reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){ //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setInt(1,limit);  //replacing the first question mark with the limit
            statement.setInt(2,offset); //replacing the first question mark with the offset
            ResultSet result = statement.executeQuery(); //making a result set and executing the query
            while(result.next()){ //for every customer in the database that returns the query
                customer = new Customer( //making a new customer
                        result.getInt("customer_id"), //adding the customers' id from databases "customer_id" field
                        result.getString("first_name"), //adding the customers' name from databases "first_name" field
                        result.getString("last_name"), //adding the customers' surname from databases "last_name" field
                        result.getString("country"), //adding the customers' country from databases "country" field
                        result.getInt("postal_code"), //adding the customers' postal code from databases "postal_code" field
                        result.getInt("phone"), //adding the customers' phone from databases "phone" field
                        result.getString("email"));//adding the customers' email from databases "email" field
                customers.add(customer);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
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
