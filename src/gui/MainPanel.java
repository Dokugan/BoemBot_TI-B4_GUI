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
    public JTextField txtTo;
    public JTextField txtVia;
    private JButton btnAddVia;
    private JButton btnHome;
    private JButton btnM_Drive;
    private JButton btnGo;
    private JButton btnStop;

    MainPanel() {
        super("BoemBot control panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        class MyGraphics extends JComponent {
            MyGraphics() {super();}


        }

        setContentPane(mainPanel);

        pack();
        //panelGrid.add(new MyGraphics());
        setVisible(true);
    }


}


