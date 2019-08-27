/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author thomasroff
 */
public class ProductEditorDialogTest {
    
    private DAO dao;
    private DialogFixture fixture;
    private Robot robot;
    
    public ProductEditorDialogTest() {
    }
    
    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(50);

	Collection<String> categories = new ArrayList<>();
	categories.add("Weapon");
	categories.add("Produce");

	dao = mock(DAO.class);

	when(dao.getCategories()).thenReturn(categories);
    }
    
    @After
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSaveProduct() {
        ProductEditorDialog dialog = new ProductEditorDialog(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
	fixture.show().requireVisible();
        
        fixture.textBox("txtProductId").enterText("10");
	fixture.textBox("txtProductName").enterText("Mandarin");
        fixture.textBox("txtProductDescription").enterText("Juicy");
	fixture.comboBox("cmbProductCategory").selectItem("Produce");
        fixture.textBox("txtProductPrice").enterText("3.50");
        fixture.textBox("txtProductQuantityInStock").enterText("120");
        
        fixture.button("btnProductSave").click();
        
        // create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
	ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

	// verify that the DAO.save method was called, and capture the passed student
	verify(dao).saveProduct(argument.capture());

	// retrieve the passed student from the captor
	Product savedProduct = argument.getValue();

	// test that the student's details were properly saved
	assertEquals("Ensure the ID was saved", "10", savedProduct.getProductId()); 
	assertEquals("Ensure the name was saved", "Mandarin", savedProduct.getName());
	assertEquals("Ensure the category was saved", "Produce", savedProduct.getCategory());
        assertEquals("Ensure the price was saved", new BigDecimal("3.50"), savedProduct.getListPrice());
        assertEquals("Ensure the quantity was saved", new BigDecimal("120"), savedProduct.getQuantityInStock());
    }
    
}
