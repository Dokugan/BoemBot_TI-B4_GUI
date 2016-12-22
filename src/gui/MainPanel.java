package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stijn on 21-12-2016.
 */
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
    }
}
