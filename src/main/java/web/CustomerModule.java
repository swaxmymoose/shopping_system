/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Status;
import dao.*;
import org.jooby.Result;


/**
 *
 * @author rofth173
 */
public class CustomerModule extends Jooby {
    
    CustomerDaoInterface customerDao = new CustomerDatabaseDAO();
    
     public CustomerModule(CustomerDaoInterface dao) {
        get("/api/customers/:username", (req) -> {
            String username = req.param("username").value();
            Customer customer = customerDao.getCustomer(username);
            if(customer == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return customerDao.getCustomer(username);
            }
        });
        
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            customerDao.save(customer);
            rsp.status(Status.CREATED);
        });
        
    }
}
