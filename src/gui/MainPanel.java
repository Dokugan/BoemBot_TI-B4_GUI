package gui;

import bluetooth.BTConnection;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JMenuBar menuBar;
    private JMenu menuBoeBot;
    private JMenu menuFile;
    public NewBoebot newBoebotPanel;

    MainPanel() {
        super("BoemBot control panel");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(550, 410));

        txtToX.addActionListener((ActionEvent e) -> {
            try {
                gridPanel.selectedX = Integer.parseInt(txtToX.getText());
                gridPanel.selectedY = Integer.parseInt(txtToY.getText());

                if(gridPanel.selectedX < gridPanel.vLines - 1 && gridPanel.selectedY < gridPanel.hLines - 1) {
                    gridPanel.drawSelected = true;
                    gridPanel.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Coördinaat(en) buiten veld");
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geen geldige invoer");
            }
        });

        txtToY.addActionListener((ActionEvent e) -> {
            try {
                gridPanel.selectedX = Integer.parseInt(txtToX.getText());
                gridPanel.selectedY = Integer.parseInt(txtToY.getText());

                if(gridPanel.selectedX < gridPanel.vLines - 1 && gridPanel.selectedY < gridPanel.hLines - 1) {
                    gridPanel.drawSelected = true;
                    gridPanel.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Coördinaat(en) buiten veld");
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geen geldige invoer");
            }
        });

        btnM_Drive.addActionListener((ActionEvent e) -> new ManualDrive());

        menuBar = new JMenuBar();
        menuBoeBot = new JMenu("BoeBot");
        menuFile = new JMenu("Bestand");

        menuBar.add(menuFile);
        menuBar.add(menuBoeBot);

        JMenuItem itemSettings = new JMenuItem("Settings");
        JMenuItem itemExit = new JMenuItem("Afsluiten");
        itemExit.addActionListener(e -> {
          System.exit(0);
        });
        menuFile.add(itemSettings);
        menuFile.add(itemExit);

        JMenuItem itemAddBB = new JMenuItem("Voeg BoeBot toe");
        itemAddBB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newBoebotPanel = new NewBoebot();
            }
        });

        JMenuItem itemProperties = new JMenuItem("Eigenschappen");
        JMenuItem itemDisconnectBB = new JMenuItem("Ontkoppel BoeBot");
        itemDisconnectBB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mp.getSelectedBB();
            }
        });

        menuBoeBot.add(itemAddBB);
        menuBoeBot.add(itemProperties);
        menuBoeBot.add(itemDisconnectBB);

        setJMenuBar(menuBar);

        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    public void fillSelectedBBBox(){
        ArrayList<String> boeBotNames = new ArrayList<>();
        for(BTConnection b: Main.bots)
        {
            boeBotNames.add(b.getName());
        }

        cboxSelectBB.setModel(new DefaultComboBoxModel(boeBotNames.toArray()));
    }

    public BTConnection getSelectedBB(){
        for(BTConnection b : Main.bots)
        {
            if(cboxSelectBB.getSelectedItem() == b.getName())
            {
                return b;
            }
        }
        return null;
    }
}


