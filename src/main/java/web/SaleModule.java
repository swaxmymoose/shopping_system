/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import dao.SaleDaoInterface;
import dao.SaleDatabaseDAO;
import domain.Sale;
import domain.SaleItem;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Result;
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
            saleDao.save(sale);
            System.out.println(sale);
            rsp.status(Status.CREATED);
            CompletableFuture.runAsync(() -> {
                try {
                    sendConfirmationEmail(sale);
                }catch(EmailException e) {
                    e.printStackTrace();
                    rsp.status(Status.SERVER_ERROR);
                }
            });
        });      
    }
    
    
    
    public void sendConfirmationEmail(Sale s) throws EmailException {
        String prodLine = "";
       
        for(SaleItem item : s.getItems()) {
            String prodName = item.getProduct().getName();
            String prodPrice = item.getSalePrice().toString();
            String prodQuantity = item.getQuantityPurchased().toString();
            String prodTotalCost = item.getItemTotal().toString();
            prodLine += "Name: " + prodName + "\tPrice: $" + prodPrice + "\tQuantity: " + prodQuantity + "\tTotal $" + prodTotalCost + "\n";
        }
        
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(2525);
        email.setFrom("megastore@megamail.com");
        email.setSubject("Thank you for your order");
        email.setMsg("Your order\n\n" + prodLine + "\nyour grand total is: $" + s.getTotal());
        email.addTo(s.getCustomer().getEmailAddress());
        email.send();
    }
}

