/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import dao.SaleDaoInterface;
import dao.SaleDatabaseDAO;
import domain.Sale;
import java.time.LocalDate;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author rofth173
 */
public class SaleModule extends Jooby {
    
    SaleDaoInterface saleDao = new SaleDatabaseDAO();
    
    public SaleModule(SaleDaoInterface dao) {
        post("/api/sales", (req, rsp) -> {           
            Sale sale = req.body().to(Sale.class);
            sale.setStatus("pending");       
            sale.setDate(LocalDate.now());
            //saleDao.save(sale);
            System.out.println(sale);
            System.err.println("complete sale module");
            rsp.status(Status.CREATED);
        });
        
    }
}

