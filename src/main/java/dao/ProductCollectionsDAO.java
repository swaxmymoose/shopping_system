/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author rofth173
 */
public class ProductCollectionsDAO {
    
    static Collection<Product> products = new ArrayList();
    
    public void saveProduct(Product p) {
        products.add(p);
    }
    
    public Collection<Product> getProducts() {
        return products;
    }
    
    public void deleteProduct(Product p) {
        products.remove(p);
    }
    
    public Collection getCategories() {
        ArrayList<String> categoryList = new ArrayList();
        for(Product p : products) {
            if(!categoryList.contains(p.getCategory())) {
                categoryList.add(p.getCategory());
            }
        }
        return categoryList;
    }
}
