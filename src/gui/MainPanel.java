package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by Stijn on 23-12-2016.
 */
public class MainPanel extends JFrame {

    private JPanel mainPanel;
    private JComboBox cboxSelectBB;
    public JTextField txtVia;
    private JButton btnAddVia;
    private JButton btnHome;
    private JButton btnM_Drive;
    private JButton btnGo;
    private JButton btnStop;
    private JButton btnTo;
    private JButton btnVia1;
    public JTextField txtToX;
    public JTextField txtToY;
    private GridPanel gridPanel;

    MainPanel() {
        super("BoemBot control panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(550, 410));

        txtToX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gridPanel.selectedX = Integer.parseInt(txtToX.getText());
                    gridPanel.selectedY = Integer.parseInt(txtToY.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Geen geldige invoer");
                }
                gridPanel.drawSelected = true;
                gridPanel.repaint();
            }
        });

        txtToY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.selectedX = Integer.parseInt(txtToX.getText());
                gridPanel.selectedY = Integer.parseInt(txtToY.getText());
                gridPanel.drawSelected = true;
                gridPanel.repaint();
            }
        });

        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
}


