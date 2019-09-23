package dao;

import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleDatabaseDAO implements SaleDaoInterface {

    private static final String url = DbConnection.getDefaultConnectionUri();

    @Override
    public void save(Sale sale) {

        Connection con = DbConnection.getConnection(url);
        try {
            try (
                    PreparedStatement insertOrderStmt = con.prepareStatement(
                              "insert into SALE (SALEDATE. STATUS, CUSTOMERID) VALUES(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement insertOrderItemStmt = con.prepareStatement(
                            "insert into SALE_ITEM (SALEPRICE, PRODUCT, QUANTITY) VALUES (?, ?, ?)");
                    PreparedStatement updateProductStmt = con.prepareStatement(
                            "UPDATE PRODUCT SET QUANTITYINSTOCK = QUANTITYINSTOCK - ? WHERE PRODUCTID = ?");) {
                
              

                // since saving and order involves multiple statements across
                // multiple tables we need to control the transaction ourselves
                // to ensure our DB remains consistent
                // turn off auto-commit which effectively starts a new transaction
                con.setAutoCommit(false);

                Customer customer = sale.getCustomer();

                // #### save the order ### //
                // add a date to the sale if one doesn't already exist
                if (sale.getDate() == null) {
                    sale.setDate(LocalDate.now());
                }

                // convert sale date into to java.sql.Timestamp
                LocalDate date = sale.getDate();
                Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());

                 insertOrderStmt.setTimestamp(1, timestamp);
                 insertOrderStmt.setString(3, sale.getCustomer().getCustomerId().toString());
                 insertOrderStmt.executeUpdate();
                // get the auto-generated order ID from the database
                ResultSet rs = insertOrderStmt.getGeneratedKeys();

                Integer orderId = null;

                if (rs.next()) {
                    orderId = rs.getInt(1);
                } else {
                    throw new DAOException("Problem getting generated Order ID");
                }

                Collection<SaleItem> items = sale.getItems();

                for(SaleItem item : items) {
                    insertOrderItemStmt.setBigDecimal(1, item.getSalePrice());
                    insertOrderItemStmt.setString(2, item.getProduct().toString());
                    insertOrderItemStmt.setBigDecimal(3, item.getQuantityPurchased());
                }

                for (SaleItem item : items) {
                    Product product = item.getProduct();
                    updateProductStmt.setBigDecimal(1, item.getQuantityPurchased());
                    updateProductStmt.setString(2, product.getProductId());
                }

                // commit the transaction
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {

            Logger.getLogger(SaleDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                // something went wrong so rollback
                con.rollback();

                // turn auto-commit back on
                con.setAutoCommit(true);

                // and throw an exception to tell the user something bad happened
                throw new DAOException(ex.getMessage(), ex);
            } catch (SQLException ex1) {
                throw new DAOException(ex1.getMessage(), ex1);
            }

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SaleDatabaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
