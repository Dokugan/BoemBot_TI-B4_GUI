package gui;

import bluetooth.BTConnection;
import bluetooth.ListPorts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class NewBoebot extends JFrame implements Runnable{
    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtLocation;
    private JButton btnAdd;
    private JComboBox combPort;

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
                    Main.bots.add(new BTConnection(txtName.getText(), combPort.getSelectedItem().toString(), 0, 0));
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

    NewBoebot(String name, String port){
        super("Nieuwe Boebot");

        ports.add(port);
        ports.add("Poorten laden...");
        combPort.setModel(new DefaultComboBoxModel(ports.toArray()));

        txtName.setText(name);

        btnAdd.setText("Opslaan");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO implement home position
                try {
                    Main.bots.add(new BTConnection(txtName.getText(), combPort.getSelectedItem().toString(), 0, 0));
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
        ListPorts.getPorts();
        combPort.setModel(new DefaultComboBoxModel(ports.toArray()));
    }
}
