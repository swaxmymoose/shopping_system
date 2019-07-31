/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 *
 * @author rofth173
 */
public class ProductCollectionsDAO {
    
    static Collection<Product> products = new HashSet();
    static Map<String, Product> idToProducts = new HashMap();

    public Product GetProductById(String id) {
        return idToProducts.get(id);
    }
    
    public void saveProduct(Product p) {
        products.add(p);
        idToProducts.put(p.getProductId(), p);
    }
    
    public Collection<Product> getProducts() {
        return products;
    }
    
    public void deleteProduct(Product p) {
        products.remove(p);
    }
    
    public Collection getCategories() {
        Collection<String> categorySet = new HashSet();
        for(Product p : products) {
            categorySet.add(p.getCategory());
        }
        return categorySet;
    }
}
