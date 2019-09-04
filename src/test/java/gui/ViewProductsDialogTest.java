/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;

import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author thomasroff
 */
public class ViewProductsDialogTest {

    private DAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product p1;
    private Product p2;

    public ViewProductsDialogTest() {
    }

    @Before
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(50);

        Collection<Product> products = new ArrayList<>();
        p1 = new Product("1", "name1", "desc1", "cat1", new BigDecimal("11.00"), new BigDecimal("22.00"));
        p2 = new Product("2", "name2", "desc2", "cat2", new BigDecimal("33.00"), new BigDecimal("44.00"));
        products.add(p1);
        products.add(p2);

        dao = mock(DAO.class);

        when(dao.getProducts()).thenReturn(products);

        // stub the deleteProduct method
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                // remove the product from the collection that getProducts() uses
                products.remove(p1);
                return null;
            }
        }).when(dao).deleteProduct(p1);
    }

    @After
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testViewAllProducts() {
        ViewProductsDialog dialog = new ViewProductsDialog(null, true, dao);

        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();

        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        assertTrue("list contains the expected product", model.contains(p1));
        assertEquals("list contains the correct number of products", 2, model.getSize());

    }

    @Test
    public void testDeleteProduct() {
        ViewProductsDialog dialog = new ViewProductsDialog(null, true, dao);

        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();

        fixture.list("lstProducts").selectItem(p1.toString());
        fixture.button("btnDeleteProduct").click();

        // ensure a confirmation dialog is displayed
        DialogFixture confirmDialog = fixture.dialog(withTitle("Select an Option").andShowing()).requireVisible();

        // click the Yes button on the confirmation dialog
        confirmDialog.button(withText("Yes")).click();

        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        verify(dao).deleteProduct(argument.capture());
        Product deletedProduct = argument.getValue();

        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        assertFalse("list contains the expected product", model.contains(deletedProduct));
        assertFalse("list contains the expected product", model.contains(p1));
        assertTrue("list contains the expected product", model.contains(p2));
    }

}
