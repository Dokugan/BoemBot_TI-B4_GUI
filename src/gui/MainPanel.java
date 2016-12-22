package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame{

    public  JPanel MainP;
    private  JPanel leftPanel;
    private  JPanel rightPanel;
    private  JTextField txtToX;
    private  JTextField txtToY;
    private  JButton btnDrive;
    private  JButton btnM_Drive;
    private  JTextField txtFromX;
    private  JTextField txtFromY;

    public MainPanel() {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainP);
        pack();

        setVisible(true);
        btnDrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFromX.setText("test");
            }
        });

        btnM_Drive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame m_Drive = new ManualDrive();
            }
        });
    }
}
