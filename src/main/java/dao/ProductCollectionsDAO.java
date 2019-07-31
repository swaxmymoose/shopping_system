/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
    static Map<String, Product> idToProduct = new HashMap();
    static Multimap<String, Product> categoryToProducts = ArrayListMultimap.create();

    public Product GetProductById(String id) {
        return idToProduct.get(id);
    }
    
    public Collection<Product> getProductsByCategory(String category) {
        return categoryToProducts.get(category);
    }
    
    public void saveProduct(Product p) {
        products.add(p);
        idToProduct.put(p.getProductId(), p);
        categoryToProducts.put(p.getCategory(), p);
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
