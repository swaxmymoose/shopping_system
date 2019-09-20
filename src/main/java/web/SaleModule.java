/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author rofth173
 */
public class SaleModule extends Jooby {
    
   // SaleDAO saleDao = new SaleDAO();
    
    public SaleModule() {
        post("/api/sales", (req, rsp) -> {           
            Sale sale = req.body().to(Sale.class);
            //saleDAO.save(sale);
            rsp.status(Status.CREATED);
        });
        
    }
}

