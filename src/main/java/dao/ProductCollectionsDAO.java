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
public class ProductCollectionsDAO implements ProductDaoInterface {
    
    private static Collection<Product> products = new HashSet();
    private static Map<String, Product> idToProduct = new HashMap();
    private static Multimap<String, Product> categoryToProducts = ArrayListMultimap.create();

    @Override
    public Product getProductById(String id) {
        return idToProduct.get(id);
    }
    
    @Override
    public Collection<Product> getProductsByCategory(String category) {
        System.out.println(categoryToProducts.get(category));
        return categoryToProducts.get(category);
    }
    
    @Override
    public void saveProduct(Product p) {
        products.add(p);
        idToProduct.put(p.getProductId(), p);
        categoryToProducts.put(p.getCategory(), p);
    }
    
    @Override
    public Collection<Product> getProducts() {
        return products;
    }
    
    @Override
    public void deleteProduct(Product p) {
        products.remove(p);
        idToProduct.remove(p.getProductId());
        categoryToProducts.remove(p.getCategory(), p); //look at what should be happening with this
        
    }
    
    @Override
    public Collection getCategories() {
        Collection<String> categorySet = new HashSet();
        for(Product p : products) {
            categorySet.add(p.getCategory());
        }
        return categorySet;
    }
}
