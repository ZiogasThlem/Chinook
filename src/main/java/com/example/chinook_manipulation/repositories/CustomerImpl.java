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

    @Override
    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            System.out.println("I am here");
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> readAll() {
        String sql = "SELECT  " +
                "customer_id, " +
                "first_name, " +
                "last_name, " +
                "country, " +
                "postal_code, " +
                "phone, " +
                "email " +
                "FROM customer";

        List<Customer> customers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
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
        //this sql query returns all the fields from table customer, for the customer with a specific id
        String sql = "SELECT * FROM customer WHERE customer_id = ?"; //passing the sql query into a string value
        Customer customer = null; //creating a new customer reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){ //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setInt(1,integer); //replacing the question mark with the integer
            ResultSet result = statement.executeQuery(); //making a result set and executing the query
            while(result.next()) {
                customer = new Customer( //making a new customer
                        result.getInt("customer_id"), //adding the customers' id from databases "customer_id" field
                        result.getString("first_name"), //adding the customers' name from databases "first_name" field
                        result.getString("last_name"), //adding the customers' surname from databases "last_name" field
                        result.getString("country"), //adding the customers' country from databases "country" field
                        result.getString("postal_code"), //adding the customers' postal code from databases "postal_code" field
                        result.getString("phone"),  //adding the customers' phone from databases "phone" field
                        result.getString("email") //adding the customers' email from databases "email" field
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); //prints the exceptions' message
        }
        return customer; //returns the customer
    }

    @Override
    public Customer readByName(String name) {

        String sql = "SELECT * FROM customer WHERE first_name LIKE ?";
        Customer customer = null;

        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+name+"%");

            ResultSet result = statement.executeQuery();
            while(result.next()){
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> page(int limit, int offset) {
        /*This sql query returns the fields customer_id, country, postal_code, first_name,
         *last_name, phone and email from table customer.
         *with the limit parameter returns only the x customers,the number x is the limit.
         *with the offset parameter skips the first y customers, the number y is the offset.*/
        String sql = """
                        SELECT customer_id, country, postal_code, 
                                first_name, last_name, phone, email 
                        FROM customer 
                        LIMIT ? 
                        OFFSET ?"""; //passing the sql query into a string value
        List<Customer> customers = new ArrayList<>(); //creating a new list of customers
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
                        result.getString("postal_code"), //adding the customers' postal code from databases "postal_code" field
                        result.getString("phone"), //adding the customers' phone from databases "phone" field
                        result.getString("email")); //adding the customers' email from databases "email" field
                customers.add(customer) ; //adding customer to the list
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage()); //prints exceptions' message
        }
        return customers; //returns the customers list
    }

    @Override
    public int insert(Customer customer) {

        String sql = """
                    INSERT INTO customer  
                    (first_name,
                    last_name,
                    country,
                    postal_code,
                    phone,
                    email)
                    VALUES (?,?,?,?,?,?)""";

        int result = 0;

        try(Connection conn = DriverManager.getConnection(url, username,password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,customer.firstName());
            statement.setString(2,customer.lastName());
            statement.setString(3,customer.country());
            statement.setString(4, customer.postalCode());
            statement.setString(5, customer.phoneNumber());
            statement.setString(6,customer.email());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;


    }

    @Override
    public int update(Customer object) {
        /*This sql query changes the fields customer_id, country, postal_code, first_name,
         *last_name, phone and email of a record in table customer
        * which has a specified id.*/
        String sql = """
                UPDATE customer 
                SET first_name=?, last_name=?, country=?,
                    postal_code=?, phone=?, email=? 
                WHERE customer_id=?"""; //passing the sql query into a string value
        int id = object.id(); //taking the id of the object
        int result=0;
        String name = object.firstName(); //taking the name of the object
        String surname = object.lastName(); //taking the surname of the object
        String country = object.country(); //taking the country of the object
        String postalCode = object.postalCode(); //taking the postal Code of the object
        String phone = object.phoneNumber(); //taking the phone of the object
        String email = object.email(); //taking the email of the object
        try(Connection conn = DriverManager.getConnection(url,username,password)) { //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql); //creating a statement for the query
            statement.setString(1,name); //replacing the first question mark with the name
            statement.setString(2,surname); //replacing the second question mark with the surname
            statement.setString(3,country); //replacing the third question mark with the country
            statement.setString(4,postalCode); //replacing the fourth question mark with the postal code
            statement.setString(5,phone); //replacing the fifth question mark with the phone
            statement.setString(6,email); //replacing the sixth question mark with the email
            statement.setInt(7,id); //replacing the seventh question mark with the id
            result = statement.executeUpdate(); //passing value into result and executing the query for the update
        } catch (SQLException e) {
            System.out.println(e.getMessage()); //prints exceptions' message
        }
        return result; //returns the result statement of the update query
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
            while(result.next()) { //for every customer in the database that returns the query
                country = new CustomerCountry(result.getString("country"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;

    }

    @Override
    public CustomerSpender highestSpender() {
        /* This sql query joins the tables customer and invoice to get the names, ids and total amount from
        * the customers.
        * At the second phase it takes the result of the previous select and takes the name, id and
        * total amount of the customers with the maximum total in the database.*/
        String sql = """
                SELECT  name, id, total
                FROM ( 
                    SELECT first_name as name, 
                    customer.customer_id as id, total
                    FROM customer
                    JOIN invoice 
                    ON customer.customer_id = invoice.customer_id) AS spenders
                WHERE total = (SELECT MAX(total) FROM invoice)"""; //passing the sql query into a string value
        CustomerSpender customerSpender = null; //creating a new customerSpender reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){  //trying to make a connection with the database
            PreparedStatement statement = conn.prepareStatement(sql);  //creating a statement for the query
            ResultSet result = statement.executeQuery();  //making a result set and executing the query
            while(result.next()) { //for every customer in the database that returns the query
                customerSpender = new CustomerSpender( //making a new customerSpender
                    result.getInt("id"), //adding the customerSpenders' id from databases "id" field
                    result.getString("name"), //adding the customerSpenders' name from databases "name" field
                    result.getDouble("total")); //adding the customerSpenders' total from databases "total" field
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());  //prints exceptions' message
        }
        return customerSpender; //returns the customerSpender
    }

    @Override
    public CustomerGenre mostPopGenre(int id) {
        /* This sql query has a lot of phases.
        * Phase 1) multiple join on the tables customer, invoice, invoice_line, track, genre
        *           to get the name, id, genre name and genre id for every record.
        * Phase 2) it takes the result of the previous multiple join and  returns the name, id,
        * genre name and counter that shows how many times a specific genre exists at the records
        * for a specific customer with the specific id.
        * Phase 2) at this phase takes the previous result and selects the genre name, customer name
        * and customer id who has the maximum counter of genre.  */
        String sql = """
                    SELECT gr_name, name, id
                    FROM(SELECT name, id, gr_name, count(genre)
                    		FROM (SELECT customer.first_name as name,
                    					 customer.customer_id as id,
                    					 track.genre_id as genre,
                    					 genre.name as gr_name
                    				FROM customer
                    				JOIN invoice
                    					ON customer.customer_id = invoice.customer_id
                    				JOIN invoice_line
                    					ON invoice.invoice_id = invoice_line.invoice_line_id
                    				JOIN track
                    					ON invoice_line.track_id = track.track_id
                    				JOIN genre
                    					ON track.genre_id = genre.genre_id)AS customers_genre
                    		WHERE id=?
                    		GROUP BY name, id,gr_name)AS something
                    WHERE (count = (SELECT MAX(count)
                    				FROM(SELECT gr_name, count(genre)
                    				FROM (SELECT customer.first_name as name,
                    							 customer.customer_id as id,
                    							 track.genre_id as genre,
                    							 genre.name as gr_name
                    						FROM customer
                    						JOIN invoice
                    							ON customer.customer_id = invoice.customer_id
                    						JOIN invoice_line
                    							ON invoice.invoice_id = invoice_line.invoice_line_id
                    						JOIN track
                    							ON invoice_line.track_id = track.track_id
                    						JOIN genre
                    							ON track.genre_id = genre.genre_id)AS customers_genre
                    				WHERE id=?
                    				GROUP BY gr_name)AS something))"""; //passing the sql query into a string value
        CustomerGenre customerGenre = null;  //creating a new customerGenre reference
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);   //creating a statement for the query
            statement.setInt(1,id); //replacing the first question mark with the id
            statement.setInt(2,id); //replacing the second question mark with the id
            ResultSet result = statement.executeQuery();  //making a result set and executing the query
            while(result.next()) { //for every customer in the database that returns the query
                customerGenre = new CustomerGenre( //making a new customerGenre
                        result.getInt("id"), //adding the customerGenres' id from databases "id" field
                        result.getString("name"), //adding the customerGenres' name from databases "name" field
                        result.getString("gr_name") //adding the customerGenres' genre name from databases "gr_name" field
                );
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());  //prints exceptions' message
        }
        return customerGenre; //returns the customerGenre
    }
}