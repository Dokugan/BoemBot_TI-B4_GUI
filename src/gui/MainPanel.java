package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainPanel extends JFrame{


    public MainPanel()
    {
        super("BoeBot Control panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1280, 720);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel p = new JPanel(null);

        JButton bMDrive = new JButton("Manual Drive");

        bMDrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Manual drive enabled");
            }
        });

        bMDrive.setBounds(100,100,150,30);

        p.add(bMDrive);

        setContentPane(p);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                System.out.println("Resize to " + getWidth() + "x" + getHeight());
            }
        });
    }

    private void initComponents(){

    }
}