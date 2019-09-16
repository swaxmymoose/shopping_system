/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thomasroff
 */
public class CustomerDatabaseDAO implements CustomerDaoInterface {
    
    private String URI = DbConnection.getDefaultConnectionUri();
    
    public CustomerDatabaseDAO() {}
    
    public CustomerDatabaseDAO(String URI) {
        this.URI = URI;
    }
    
    @Override
    public void save(Customer c) {
     String sql = "insert into customer (username, firstname, surname, password, emailAddress, shippingAddress) values (?,?,?,?,?,?)";
        
        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            
            // copy the data from the customer domain object into the SQL parameters
            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getFirstName());
            stmt.setString(3, c.getSurname());
            stmt.setString(4, c.getPassword());
            stmt.setString(5, c.getEmailAddress());
            stmt.setString(6, c.getShippingAddress());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Customer getCustomer(String username) {
    String sql = "select * from customer where username = ?";
        
        try (
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
        ) {
            
            // set the parameter
            stmt.setString(1, username);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String customerId = rs.getString("customerId");
                String usrname = rs.getString("username");
                String firstname = rs.getString("firstName");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                String emailAddress = rs.getString("emailAddress");
                String shippingAddress = rs.getString("shippingAddress");

                return new Customer(Integer.parseInt(customerId), usrname, firstname, surname, password, emailAddress, shippingAddress);

            } else {
                // no customers matches given ID so return null
                return null;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Boolean validateCredentials(String username, String password) {
        return false; /* DO THIS */
    }
}
