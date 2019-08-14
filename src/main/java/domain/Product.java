/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import net.sf.oval.constraint.*;

/**
 *
 * @author rofth173
 */
public class Product {
    @NotNull(message = "Id must be provided.")
    @NotBlank(message = "Id must be provided.")
    private String productId;
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")
    private String name;
    @NotNull(message = "Description must be provided.")
    @NotBlank(message = "Description must be provided.")
    private String description;
    @NotNull(message = "Category must be provided.")
    @NotBlank(message = "Category must be provided.")
    private String category;
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    private BigDecimal listPrice;
    @NotNull(message = "Quantity in stock must be provided.")
    @NotNegative(message = "Quantity in stock must be zero or greater.")
    private BigDecimal quantityInStock;

    public Product(String productId, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.listPrice = listPrice;
        this.quantityInStock = quantityInStock;
    }
    
    public Product(){}
    
    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", description=" + description + ", category=" + category + ", listPrice=" + listPrice + ", quantityInStock=" + quantityInStock + '}';
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(BigDecimal quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
