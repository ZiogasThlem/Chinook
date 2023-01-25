package com.example.chinook_manipulation.repositories;

import com.example.chinook_manipulation.models.Customer;
import com.example.chinook_manipulation.models.CustomerCountry;
import com.example.chinook_manipulation.models.CustomerGenre;
import com.example.chinook_manipulation.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final String url;
    private final String username;
    private final String password;

    @Autowired
    public CustomerRepositoryImpl(@Value("${spring.datasource.url}") String url,
                                  @Value("${spring.datasource.username}") String username,
                                  @Value("${spring.datasource.password}") String password) {

        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> readAll() {

        // Selecting the required fields from customer table
        String sql = "SELECT  " +
                "customer_id, " +
                "first_name, " +
                "last_name, " +
                "country, " +
                "postal_code, " +
                "phone_number, " +
                "email " +
                "FROM customer"; // passing the sql query into a string value

        List<Customer> customers = new ArrayList<>(); // creating an ArrayList that servers as a container for Customer objects
        try(Connection conn = DriverManager.getConnection(url, username,password)) { //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query

            ResultSet result = statement.executeQuery(); //making a result set and executing the query
            while(result.next()) { //for every customer in the database that returns the query
                Customer customer = new Customer(
                        result.getInt("customer_id"), //adding the customers' id from databases "customer_id" field
                        result.getString("first_name"), //adding the customers' name from databases "first_name" field
                        result.getString("last_name"), //adding the customers' surname from databases "last_name" field
                        result.getString("country"), //adding the customers' country from databases "country" field
                        result.getInt("postal_code"), //adding the customers' postal code from databases "postal_code" field
                        result.getInt("phone_number"), //adding the customers' phone from databases "phone" field
                        result.getString("email") //adding the customers' email from databases "email" field
                );
                customers.add(customer); // adding each customer object to the customers ArrayList
            }
        } catch (SQLException e) { //prints the exceptions' message
            e.printStackTrace();
        }
        return customers; //returns customers ArrayList
    }

    @Override
    public Customer readByID(int integer) {

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
    public Customer readByName(String name) {

        // selecting every entry from customer which first name field includes String provided by "name" parameter
        String sql = "SELECT * FROM customer WHERE first_name LIKE %?%";

        Customer customer = null; //creating a new customer reference
        try(Connection conn = DriverManager.getConnection(url, username,password)) { //trying to make a connection with the database

            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setString(1, name); //replacing the question mark with name parameter
            ResultSet result = statement.executeQuery();  //making a result set and executing the query
            while(result.next()){ //for every customer in the database that returns the query

                customer = new Customer( //making a new customer

                        result.getInt("customer_id"), //adding the customers' id from databases "customer_id" field
                        result.getString("first_name"), //adding the customers' name from databases "first_name" field
                        result.getString("last_name"), //adding the customers' surname from databases "last_name" field
                        result.getString("country"), //adding the customers' country from databases "country" field
                        result.getInt("postal_code"), //adding the customers' postal code from databases "postal_code" field
                        result.getInt("phone"),  //adding the customers' phone from databases "phone" field
                        result.getString("email") //adding the customers' email from databases "email" field
                );
            }
        } catch (SQLException e) { //prints the exceptions' message
            e.printStackTrace();
        }
        return customer; //returns the customer
    }

    @Override
    public List<Customer> page(int limit, int offset) {

        String sql = "SELECT first_name, last_name, phone, email FROM customer LIMIT ? OFFSET ?"; //passing the sql query into a string value
        List<Customer> customers = new ArrayList<>(); //creating a new list of customers
        Customer customer; //creating a new customer reference

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
    public int insert(Customer customer) {

        // passing all the required fields to insert a new customer entry to the query
        String sql = """
                    INSERT INTO customer\s
                    first_name,
                    last_name,
                    tcountry,
                    postal_code,
                    phone_number,
                    email)
                    VALUES (?,?,?,?,?,?)""";

        int result = 0; // initialize result variable

        try(Connection conn = DriverManager.getConnection(url, username,password)) { //trying to make a connection with the database

            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setString(1,customer.first_name()); //setting first_name field for new customer entry
            statement.setString(2,customer.last_name());//setting last_name field for new customer entry
            statement.setString(3,customer.country());//setting country field for new customer entry
            statement.setInt(4, customer.postal_code());//setting postal_code field for new customer entry
            statement.setInt(5, customer.phone_number());//setting phone_number field for new customer entry
            statement.setString(6,customer.email());//setting email field for new customer entry

            result = statement.executeUpdate(); // executing the sql statement and passing it to result variable

        } catch (SQLException e) { //prints exceptions' message
            e.printStackTrace();
        }
        return result; //returns the result statement of the update query

    }

    @Override
    public int update(Customer object) {
        //passing the sql query into a string value
        String sql = "UPDATE customer SET first_name=?, last_name=?, " +
                "country=?, postal_code=?, phone=?, email=? WHERE customer_id=?";
        int id = object.id(); //taking the id of the object
        int result=0;
        String name = object.first_name(); //taking the name of the object
        String surname = object.last_name(); //taking the surname of the object
        String country = object.country(); //taking the country of the object
        int postalCode = object.postal_code(); //taking the postal Code of the object
        int phone = object.phone_number(); //taking the phone of the object
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
        } catch (SQLException e) { //prints exceptions' message
            System.out.println(e.getMessage()); //prints exceptions' message
        }
        return result; //returns the result statement of the update query
    }

    @Override
    public CustomerCountry countryWithMostCustomers() {

        /* Selects a country from customer table, by descending order,
        with a limit of 1, so it's the most common occurrence of "country"
        in customer table, thus the country with the most customers. */
        String sql = """
                SELECT country
                FROM customer
                GROUP BY country
                ORDER BY count(country) DESC
                LIMIT 1;""";

        CustomerCountry country = null; //creating a new CustomerCountry reference
        try (Connection conn = DriverManager.getConnection(url, username, password)) { //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            ResultSet result = statement.executeQuery(); //making a result set and executing the query
            while (result.next()) { //for every country in customer table in the database that returns the query
                country = new CustomerCountry(result.getString("country"));
            }
        } catch (SQLException e) { //prints exceptions' message
            e.printStackTrace();
        }

        return country;  // returning the required CustomerCountry object
    }

    @Override
    public CustomerSpender highestSpender() {
        //passing the sql query into a string value
        String sql= "SELECT  name, id, total" +
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
        //passing the sql query into a string value
        String sql = "SELECT gr_name, name, id FROM(SELECT name, id, gr_name, count(genre) " +
                "FROM (SELECT customer.first_name as name, " +
                " customer.customer_id as id, " +
                " track.genre_id as genre, " +
                " genre.name as gr_name " +
                "FROM customer JOIN invoice ON customer.customer_id = invoice.customer_id " +
                "JOIN invoice_line ON invoice.invoice_id = invoice_line.invoice_line_id " +
                "JOIN track ON invoice_line.track_id = track.track_id " +
                "JOIN genre ON track.genre_id = genre.genre_id)AS customers_genre " +
                "WHERE id=? " +
                "GROUP BY name, id, gr_name)AS something " +
                "WHERE (count = (SELECT MAX(count) FROM(SELECT gr_name, count(genre) " +
                "FROM (SELECT customer.first_name as name, customer.customer_id as id, " +
                "track.genre_id as genre, genre.name as gr_name " +
                "FROM customer JOIN invoice ON customer.customer_id = invoice.customer_id " +
                "JOIN invoice_line ON invoice.invoice_id = invoice_line.invoice_line_id " +
                "JOIN track tON invoice_line.track_id = track.track_id " +
                "JOIN genre ON track.genre_id = genre.genre_id)AS customers_genre " +
                "WHERE id=? " +
                "GROUP BY gr_name)AS something))";
        CustomerGenre customerGenre = null;  //creating a new customerGenre reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);   //creating a statement for the query
            ResultSet result = statement.executeQuery();  //making a result set and executing the query
            customerGenre = new CustomerGenre( //making a new customerGenre
                    result.getInt("id"), //adding the customerGenres' id from databases "id" field
                    result.getString("name"), //adding the customerGenres' name from databases "name" field
                    result.getString("gr_name") //adding the customerGenres' genre name from databases "gr_name" field
            );
        }catch (SQLException e) {
            System.out.println(e.getMessage());  //prints exceptions' message
        }
        return customerGenre; //returns the customerGenre
    }

}
