/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.jooby.Jooby;
import dao.ProductDaoInterface;

/**
 *
 * @author rofth173
 */
public class ProductModule extends Jooby{
    
    public ProductModule(ProductDaoInterface dao) {
        get("/api/products", () -> dao.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return dao.getProductById(id);
        });
        
        get("/api/categories", () -> dao.getCategories());
        get("/api/categories/:cat", (req) -> {
            String cat = req.param("cat").value();
            return dao.getProductById(cat);
        });
        
    }
}
