/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.CodeRulers.display;

import dev.CodeRulers.game.CodeRulers;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the window which will display our program.
 * 
 * @author Sean Zhang
 */
public class Display extends JFrame {
    //This is the image icon that the program will display when it is run.
    private ImageIcon logo;
    
    //this the JPanel that will be responsible for all graphical drawing to the
    //screen in the program.
    private JPanel panel1;
    
    //Jframe variables:
    private String title; //title of the jframe
    private int width,height; //width and height of jframe
    
    /**
     * The Constructor for the Display Class. In this block of code, all graphical
     * components of the game are initialized.
     * @param title The top window border text
     * @param imageLogo The CodeRulers logo to be displayed on the task bar
     * @param r The game being displayed in the frame
     */
    public Display(String title, String imageLogo, CodeRulers r) {
        //Message that tells user that the display has been intialized.
        System.out.println("Display Initialized.");
        
        //creates the new ImageIcon object.
        logo = new ImageIcon(getClass().getResource("/resources/logo/" + imageLogo));
        
        //intializes graphical components of the game.
        initComponents();
        
        //creates a new Panel object and passes in the game object, CodeRulers r.
        panel1 = new Panel(r);
        
        //additional things to add to the JPanel, since I do not have access to
        //the code in initComponents();
	this.add(panel1);
        this.setIconImage(logo.getImage());
        this.setTitle(title);
        this.pack();
        
        //display the JFrame.
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel1;
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
