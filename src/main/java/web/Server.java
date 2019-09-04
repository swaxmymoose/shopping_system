/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAO;
import dao.ProductDatabaseDAO;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;

/**
 *
 * @author rofth173
 */
public class Server extends Jooby {
    
    DAO productDao = new ProductDatabaseDAO();
    
    public Server() {
        port(8080);
        get("/api/products", () -> productDao.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return productDao.getProductById(id);
        });
        
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }   
}