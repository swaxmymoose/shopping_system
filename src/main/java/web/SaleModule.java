/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import dao.SaleDaoInterface;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author rofth173
 */
public class SaleModule extends Jooby {
    
   // SaleDAO saleDao = new SaleDAO();
    
    public SaleModule(SaleDaoInterface dao) {
        post("/api/sales", (req, rsp) -> {           
            Sale sale = req.body().to(Sale.class);
            //saleDAO.save(sale);
            System.out.println(sale);
            System.err.println("complete sale module");
            rsp.status(Status.CREATED);
        });
        
    }
}

