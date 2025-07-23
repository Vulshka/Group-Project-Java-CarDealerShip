/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cardealertrydesign;

/**
 *
 * @author Jericho Mico
 */
public class CARDEALERTRYDESIGN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//                java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CarJframe().setVisible(true);
//            }
//        });

//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new User().setVisible(true);
//            }
//        });
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Start with Login form instead of CarJframe
                new Login().setVisible(true);
            }
        });
    }
    
}
