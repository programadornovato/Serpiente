/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serpiente;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Dell
 */
public class Serpiente {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Configura FlatLaf antes de inicializar los componentes
        UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });

    }
}
