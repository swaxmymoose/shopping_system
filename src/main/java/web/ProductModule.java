/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAO;
import org.jooby.Jooby;

/**
 *
 * @author rofth173
 */
public class ProductModule extends Jooby{
    
    public ProductModule(DAO dao) {
        get("/api/products", () -> dao.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return dao.getProductById(id);
        });
        
    }
}
