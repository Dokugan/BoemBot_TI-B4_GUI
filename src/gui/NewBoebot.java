package gui;

import bluetooth.BTConnection;
import bluetooth.ListPorts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewBoebot extends JFrame implements Runnable{
    private JPanel mainPanel;
    private JTextField txtName;
    private JButton btnAdd;
    private JComboBox combPort;
    private JTextField txtPosX;
    private JTextField txtPosY;
    private JTextField txtHomeX;
    private JTextField txtHomeY;

    public static ArrayList<String> ports = new ArrayList<>();

    NewBoebot(){
        super("Nieuwe Boebot");

        ports.add("Poorten laden...");
        combPort.setModel(new DefaultComboBoxModel(ports.toArray()));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO implement home position
                try {
                    Main.bots.add(new BTConnection(txtName.getText(), combPort.getSelectedItem().toString(), Integer.parseInt(txtPosX.getText()), Integer.parseInt(txtPosY.getText())));
                    Main.mp.fillSelectedBBBox();
                    NewBoebot.super.dispose();
                    Main.mp.gridPanel.repaint();
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Velden niet juist ingevoerd");
                }

            }
        });

        setContentPane(mainPanel);
        pack();
        setVisible(true);

        Thread t = new Thread(this, "t1");

        t.start();
    }

    NewBoebot(String name, String port, int posx, int posy){
        super("Bewerk Boebot");

        ports.clear();
        ports.add(port);
        ports.add("Poorten laden...");
        combPort.setModel(new DefaultComboBoxModel(ports.toArray()));

        txtName.setText(name);
        txtPosX.setText(Integer.toString(posx));
        txtPosY.setText(Integer.toString(posy));

        btnAdd.setText("Opslaan");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO implement home position
                try {
                    Main.mp.getSelectedBB().setName(txtName.getText());
                    Main.mp.getSelectedBB().setCurrentPos(Integer.parseInt(txtPosX.getText()), Integer.parseInt(txtPosY.getText()));
                    Main.mp.fillSelectedBBBox();
                    NewBoebot.super.dispose();
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Velden niet juist ingevoerd");
                }
            }
        });

        setContentPane(mainPanel);
        pack();
        setVisible(true);

        Thread t = new Thread(this, "t1");

        t.start();
    }

    public void run(){
        //ListPorts.getPorts();
        //combPort.setModel(new DefaultComboBoxModel(ports.toArray()));
    }
}
