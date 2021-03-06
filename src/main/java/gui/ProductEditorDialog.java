package gui;

import dao.*;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import javax.swing.JOptionPane;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.exception.ConstraintsViolatedException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rofth173
 */
public class ProductEditorDialog extends javax.swing.JDialog {
    
    private final ProductDaoInterface dao;
    SimpleListModel myModel = new SimpleListModel();


    /**
     * Creates new form ProductEditorDialog
     */
    public ProductEditorDialog(java.awt.Frame parent, boolean modal, ProductDaoInterface dao) {
        super(parent, modal);
        initComponents();
        this.dao = dao;
        Collection collection = dao.getCategories();
        cmbProductCategory.setEditable(true);
        myModel.updateItems(collection);
        cmbProductCategory.setModel(myModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProductId = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProductDescription = new javax.swing.JTextArea();
        lblProductDescription = new javax.swing.JLabel();
        lblProductCategory = new javax.swing.JLabel();
        cmbProductCategory = new javax.swing.JComboBox<>();
        lblProductPrice = new javax.swing.JLabel();
        txtProductPrice = new javax.swing.JTextField();
        lblQuantityInStock = new javax.swing.JLabel();
        txtProductQuantityInStock = new javax.swing.JTextField();
        btnProductSave = new javax.swing.JButton();
        btnProductCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblProductId.setText("Product ID:");

        txtProductId.setName("txtProductId"); // NOI18N

        lblProductName.setText("Product Name:");

        txtProductName.setName("txtProductName"); // NOI18N

        txtProductDescription.setColumns(20);
        txtProductDescription.setRows(5);
        txtProductDescription.setName("txtProductDescription"); // NOI18N
        jScrollPane1.setViewportView(txtProductDescription);

        lblProductDescription.setText("Product Description:");

        lblProductCategory.setText("Category:");

        cmbProductCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProductCategory.setName("cmbProductCategory"); // NOI18N

        lblProductPrice.setText("Price:");

        txtProductPrice.setName("txtProductPrice"); // NOI18N

        lblQuantityInStock.setText("Quantity in Stock:");

        txtProductQuantityInStock.setName("txtProductQuantityInStock"); // NOI18N

        btnProductSave.setText("Save");
        btnProductSave.setName("btnProductSave"); // NOI18N
        btnProductSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductSaveActionPerformed(evt);
            }
        });

        btnProductCancel.setText("Cancel");
        btnProductCancel.setName("btnProductCancel"); // NOI18N
        btnProductCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblProductId, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductName)
                            .addComponent(txtProductId)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductDescription)
                            .addComponent(lblProductCategory, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(cmbProductCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblQuantityInStock)
                            .addComponent(lblProductPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductPrice)
                            .addComponent(txtProductQuantityInStock)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnProductSave, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(btnProductCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductId)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductName)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProductCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductPrice)
                    .addComponent(txtProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantityInStock)
                    .addComponent(txtProductQuantityInStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProductSave)
                    .addComponent(btnProductCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductSaveActionPerformed
        // TODO add your handling code here:
        try {
            String productId = txtProductId.getText();
            String productName = txtProductName.getText();
            String productDescription = txtProductDescription.getText();
            String productCategory = (String) cmbProductCategory.getSelectedItem();
            BigDecimal productPrice = new BigDecimal(txtProductPrice.getText());
            BigDecimal productQuantityInStock = new BigDecimal(txtProductQuantityInStock.getText());

            Product newProduct = new Product();
            newProduct.setProductId(productId);
            newProduct.setName(productName);
            newProduct.setDescription(productDescription);
            newProduct.setCategory(productCategory);
            newProduct.setListPrice(productPrice);
            newProduct.setQuantityInStock(productQuantityInStock);
            
            new Validator().assertValid(newProduct);
            dao.saveProduct(newProduct);
            dispose();
        }catch(NumberFormatException ex) {
            JOptionPane JOptionPane = new JOptionPane();
            JOptionPane.showMessageDialog(this,"You have entered a price or quantity that is not a valid number."
            ,"Input Error", JOptionPane.ERROR_MESSAGE);
        }catch (ConstraintsViolatedException ex) {
            ConstraintViolation[] violations = ex.getConstraintViolations();
            String msg = "Please fix the following input problems:";
            for (ConstraintViolation cv : violations) {
            msg += "\n •" + cv.getMessage();
        }
            JOptionPane.showMessageDialog(this, msg, "Input Error",
            JOptionPane.ERROR_MESSAGE);
        }catch (DAOException ex) {
            JOptionPane JOptionPane = new JOptionPane();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnProductSaveActionPerformed

    private void btnProductCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnProductCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProductCancel;
    private javax.swing.JButton btnProductSave;
    private javax.swing.JComboBox<String> cmbProductCategory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProductCategory;
    private javax.swing.JLabel lblProductDescription;
    private javax.swing.JLabel lblProductId;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblProductPrice;
    private javax.swing.JLabel lblQuantityInStock;
    private javax.swing.JTextArea txtProductDescription;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductPrice;
    private javax.swing.JTextField txtProductQuantityInStock;
    // End of variables declaration//GEN-END:variables
}
