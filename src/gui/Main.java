package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String args[])
    {
        JPanel MainPan = new MainPanel().MainP;

        JFrame frame = new JFrame("MainPanel");
        frame.setContentPane(MainPan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
