package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainPanel extends JFrame{

    private JPanel p;
    JPanel innerPanelLeft;
    JPanel innerPanelRight;

    public MainPanel()
    {
        super("BoeBot Control panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1280, 720);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();

        setContentPane(p);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                System.out.println("Resize to " + getWidth() + "x" + getHeight());
                p.repaint();
            }
        });
    }

    private void initComponents(){

        p = new JPanel(new FlowLayout());
        innerPanelLeft = new JPanel(new GridLayout(2,2, 10, 10));
        innerPanelRight = new JPanel();

        innerPanelLeft.setBackground(Color.LIGHT_GRAY);
        innerPanelRight.setBackground(Color.LIGHT_GRAY);

        JTextField txtFrom = new JTextField();

        JTextField txtTo = new JTextField();

        innerPanelLeft.add(new JLabel("Van:"));
        innerPanelLeft.add(txtFrom);
        innerPanelLeft.add(new JLabel("Naar:"));
        innerPanelLeft.add(txtTo);

        p.add(innerPanelLeft);
        p.add(innerPanelRight);

//        JButton bMDrive = new JButton("Manual Drive");
//
//        bMDrive.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Manual drive enabled");
//            }
//        });
//
//        bMDrive.setBounds(getSize().width / 2,100,150,30);
//
//        p.add(bMDrive);
    }
}