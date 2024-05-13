package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

public class UserInterface extends javax.swing.JFrame{
    public UserInterface(){
        //Container pane = aFrame.getContentPane()...
        JButton button = new JButton("Button 1 (PAGE_START)");
        add(button, BorderLayout.PAGE_START);

        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        button = new JButton("Button 2 (CENTER)");
        button.setPreferredSize(new Dimension(200, 100));
        add(button, BorderLayout.CENTER);

        button = new JButton("Button 3 (LINE_START)");
        add(button, BorderLayout.LINE_START);

        button = new JButton("Long-Named Button 4 (PAGE_END)");
        add(button, BorderLayout.PAGE_END);

        button = new JButton("5 (LINE_END)");
        add(button, BorderLayout.LINE_END);
    }
}
