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
import dao.DbConnection;

/**
 *
 * @author rofth173
 */
public class ProductDatabaseDAO implements DAO {
    
    private static String URI = DbConnection.getDefaultConnectionUri();
    
    public ProductDatabaseDAO() {}
    
    public ProductDatabaseDAO(String URI) {
        this.URI = URI;
    }
   
    @Override
    public void deleteProduct(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProductById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from Product order by productId";
        
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
                String productId = rs.getString("ProductId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                BigDecimal listprice = rs.getBigDecimal("listprice");
                BigDecimal quantityInStock = rs.getBigDecimal("quantityInStock");

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
    public Collection<Product> getProductsByCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveProduct(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
