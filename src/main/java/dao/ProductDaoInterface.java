/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author thomasroff
 */
public interface ProductDaoInterface {

    void deleteProduct(Product p);

    Collection getCategories();

    Product getProductById(String id);

    Collection<Product> getProducts();

    Collection<Product> getProductsByCategory(String category);

    void saveProduct(Product p);
    
}
