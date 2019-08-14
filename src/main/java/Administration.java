
import dao.ProductCollectionsDAO;
import gui.MainMenuFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rofth173
 */
public class Administration {
    public static void main(String[] args) {
        System.out.println("main method!");
        //MainMenuFrame frame = new MainMenuFrame(new ProductDatabaseDAO());
        MainMenuFrame frame = new MainMenuFrame(new ProductCollectionsDAO());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
