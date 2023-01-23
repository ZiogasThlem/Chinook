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
                    result.getInt("customer_id"), //adding the customers' id from databases "customer_id" field
                    result.getString("first_name"), //adding the customers' name from databases "first_name" field
                    result.getString("last_name"), //adding the customers' surname from databases "last_name" field
                    result.getString("country"), //adding the customers' country from databases "country" field
                    result.getInt("postal_code"), //adding the customers' postal code from databases "postal_code" field
                    result.getInt("phone"),  //adding the customers' phone from databases "phone" field
                    result.getString("email") //adding the customers' email from databases "email" field
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
                        result.getString("email")); //adding the customers' email from databases "email" field
                customers.add(customer); //adding customer to the list
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage()); //prints exceptions' message
        }
        return customers; //returns the customers list
    }

        @Override
    public int insert(Customer object) {
        return 0;
    }

    @Override
    public int update(Customer object) {
        //passing the sql query into a string value
        String sql = "UPDATE customer SET first_name=?, last_name=?, " +
                "country=?, postal_code=?, phone=?, email=? WHERE customer_id=?";
        int id = object.id(); //taking the id of the object
        int result=0;
        String name = object.firstName(); //taking the name of the object
        String surname = object.lastName(); //taking the surname of the object
        String country = object.country(); //taking the country of the object
        int postalCode = object.postalCode(); //taking the postal Code of the object
        int phone = object.phoneNumber(); //taking the phone of the object
        String email = object.email(); //taking the email of the object
        try(Connection conn = DriverManager.getConnection(url,username,password)) { //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setString(1,name); //replacing the first question mark with the name
            statement.setString(2,surname); //replacing the second question mark with the surname
            statement.setString(3,country); //replacing the third question mark with the country
            statement.setInt(4,postalCode); //replacing the fourth question mark with the postal code
            statement.setInt(5,phone); //replacing the fifth question mark with the phone
            statement.setString(6,email); //replacing the sixth question mark with the email
            statement.setInt(7,id); //replacing the seventh question mark with the id
            result = statement.executeUpdate(); //passing value into result and executing the query for the update
        } catch (SQLException e) {
            System.out.println(e.getMessage()); //prints exceptions' message
        }
        return result; //returns the result statement of the update query
    }

    @Override
    public String countryWithMostCustomers() {
        return null;
    }

    @Override
    public CustomerSpender highestSpender() {
        //passing the sql query into a string value
        String sql = "SELECT  name, id, total" +
                "FROM (" +
                "SELECT first_name as name, " +
                "customer.customer_id as id," +
                "total" +
                "FROM customer" +
                "JOIN invoice " +
                "ON customer.customer_id = invoice.customer_id) AS spenders" +
                "WHERE total = (SELECT MAX(total) FROM invoice)";
        CustomerSpender customerSpender = null; //creating a new customerSpender reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){  //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql);  //creating a statement for the query
            ResultSet result = statement.executeQuery();  //making a result set and executing the query
            customerSpender = new CustomerSpender( //making a new customerSpender
                    result.getInt("name"), //adding the customerSpenders' name from databases "name" field
                    result.getString("id"), //adding the customerSpenders' id from databases "id" field
                    result.getDouble("total")); //adding the customerSpenders' total from databases "total" field
        }catch (SQLException e) {
            System.out.println(e.getMessage());  //prints exceptions' message
        }
        return customerSpender; //returns the customerSpender
    }

    @Override
    public CustomerGenre mostPopGenre() {
        return null;
    }
}
