package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stijn on 30-12-2016.
 */
public class GridProperties extends JFrame{
    private JPanel mainPanel;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JButton btnSubmit;

    GridProperties()
    {
        super("Grid eigenschappen");

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.mp.gridPanel.hLines = Integer.parseInt(txtHeight.getText()) + 1;
                    Main.mp.gridPanel.vLines = Integer.parseInt(txtWidth.getText()) + 1;
                    Main.mp.gridPanel.repaint();
                    GridProperties.super.dispose();
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Velden niet juist ingevoerd");
                }
            }
        });

        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
}
