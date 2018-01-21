/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.CodeRulers.display;

import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author seanz
 */
public class Display extends JFrame {
    //this is the logo for the game
    private ImageIcon logo;
    
    //Jframe variables:
    private String title; //title of the jframe
    private int width,height; //width and height of jframe
    
    public JPanel getPanel() {
        return mapPanel;
    }

    
    /**
     * The Constructor for the Display Class. In this block of code, all graphical
     * components of the game are initialized.
     */
    public Display(String title, String imageLogo) {

        
        System.out.println("Display Initialized.");
        logo = new ImageIcon(getClass().getResource("/resources/logo/" + imageLogo));
        initComponents();
        this.setVisible(true);
        this.setIconImage(logo.getImage());
        this.setTitle(title);
    }
    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startPanel = new javax.swing.JPanel();
        mapPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(new java.awt.CardLayout());

        mapPanel.setBackground(new java.awt.Color(255, 255, 255));
        mapPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mapPanel.setFocusable(false);
        mapPanel.setPreferredSize(new java.awt.Dimension(768, 768));
        mapPanel.setRequestFocusEnabled(false);
        mapPanel.setVerifyInputWhenFocusTarget(false);
        mapPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(startPanel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mapPanel;
    private javax.swing.JPanel startPanel;
    // End of variables declaration//GEN-END:variables
}
