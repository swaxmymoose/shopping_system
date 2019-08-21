/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author rofth173
 */
public class ProductDatabaseDAO implements DAO {
    
    private String URI = DbConnection.getDefaultConnectionUri();
    
    public ProductDatabaseDAO() {}
    
    public ProductDatabaseDAO(String URI) {
        this.URI = URI;
    }
   
    @Override
    public void deleteProduct(Product p) {
        String sql = "delete from product where productid = ?";
        
        try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);

            // set the parameter
            stmt.setString(1, p.getProductId());
            
            // execute the update
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection getCategories() {
        String sql = "select distinct category from product";
        
        try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);

            // execute the query
            ResultSet rs = stmt.executeQuery();
            
            List<String> categories = new ArrayList<>();
            
            while(rs.next()) {
                categories.add(rs.getString("category"));
            }
            
            return categories;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }

    @Override
    public Product getProductById(String id) {
        String sql = "select * from product where productid = ?";
        
        try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            
            // set the parameter
            stmt.setString(1, id);

            // execute the query
            ResultSet rs = stmt.executeQuery();

            // query only returns a single result, so use 'if' instead of 'while'
            if (rs.next()) {
                String productId = rs.getString("productid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listprice = rs.getBigDecimal("listprice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityinstock");

                return new Product(productId, name, description, category, listprice, quantityInStock);

            } else {
                // no products matches given ID so return null
                return null;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from product order by productId";
        
        try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productId = rs.getString("productid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listprice = rs.getBigDecimal("listprice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityinstock");

                // use the data to create a product object
                Product p = new Product(productId, name, description, category, listprice, quantityInStock);

                // and put it in the collection
                products.add(p);
            }
            
            return products;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Collection<Product> getProductsByCategory(String categoryToFilter) {
       String sql = "select * from product where category = ?";
       try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            
            // set the parameter
            stmt.setString(1, categoryToFilter);
            
            // execute the query
            ResultSet rs = stmt.executeQuery();

            // Using a List to preserve the order in which the data was returned from the query.
            List<Product> products = new ArrayList<>();

            // iterate through the query results
            while (rs.next()) {

                // get the data out of the query
                String productId = rs.getString("productid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listprice = rs.getBigDecimal("listprice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityinstock");

                // use the data to create a product object
                Product p = new Product(productId, name, description, category, listprice, quantityInStock);

                // and put it in the collection
                products.add(p);
            }
            
            return products;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void saveProduct(Product p) {
        
        String sql = "insert into product (productId, name, description, category, listprice, quantityinstock) values (?,?,?,?,?,?)";
        
        try {
            // get a connection to the database
            Connection dbCon = DbConnection.getConnection(URI);
            
            // create the statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            
            // copy the data from the product domain object into the SQL parameters
            stmt.setString(1, p.getProductId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setString(4, p.getCategory());
            stmt.setBigDecimal(5, p.getListPrice());
            stmt.setBigDecimal(6, p.getQuantityInStock());

            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
   
}
