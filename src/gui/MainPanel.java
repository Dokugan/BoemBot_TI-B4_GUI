package gui;

import bluetooth.BTConnection;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stijn on 23-12-2016.
 */
public class MainPanel extends JFrame implements Runnable{

    private JPanel mainPanel;
    private JComboBox cboxSelectBB;
    private JButton btnAddVia;
    private JButton btnDelVia;
    private JButton btnHome;
    private JButton btnM_Drive;
    private JButton btnGo;
    private JButton btnStop;
    public JButton btnTo;
    public JButton btnVia1;
    public GridPanel gridPanel;
    public JTextField txtViaX0;
    public JTextField txtViaY0;
    private JMenuBar menuBar;
    private JMenu menuBoeBot;
    private JMenu menuFile;
    public NewBoebot editBoeBotPanel;
    public JTextField txtToX;
    public JTextField txtToY;
    private JPanel panelVia;
    public NewBoebot newBoebotPanel;

    ArrayList<Coordinate> viaPoints = new ArrayList<>();

    MainPanel() {
        super("BoemBot control panel");
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                for (BTConnection b : Main.bots)
                    b.disconnect();

                System.out.println("BT Connections terminated");
                System.exit(0);
            }
        };

        addWindowListener(exitListener);

        setMinimumSize(new Dimension(550, 410));

        txtToX.addActionListener((ActionEvent e) -> {
            try {
                gridPanel.selectedToX = Integer.parseInt(txtToX.getText());
                gridPanel.selectedToY = Integer.parseInt(txtToY.getText());

                if(gridPanel.selectedToX < gridPanel.vLines - 1 && gridPanel.selectedToY < gridPanel.hLines - 1) {
                    gridPanel.drawSelectedTo = true;
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
                gridPanel.selectedToX = Integer.parseInt(txtToX.getText());
                gridPanel.selectedToY = Integer.parseInt(txtToY.getText());

                if(gridPanel.selectedToX < gridPanel.vLines - 1 && gridPanel.selectedToY < gridPanel.hLines - 1) {
                    gridPanel.drawSelectedTo = true;
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

        btnTo.addActionListener((ActionEvent e) -> {
            this.gridPanel.btnToPressed = true;
            this.btnTo.setEnabled(false);
        });
        btnVia1.addActionListener((ActionEvent e) -> {
            this.gridPanel.btnViaPressed = true;
            this.btnVia1.setEnabled(false);
        });

        btnAddVia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viaPoints.add(new Coordinate(Integer.parseInt(txtViaX0.getText()), Integer.parseInt(txtViaY0.getText())));
                } catch(IllegalArgumentException ex){

                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedBB().sendData(255);
            }
        });

        btnGo.addActionListener(e -> {
            getSelectedBB().sendData(254);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            getSelectedBB().sendData(Integer.parseInt(txtToX.getText()));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            getSelectedBB().sendData(Integer.parseInt(txtToY.getText()));

            if(txtViaX0.getText().length() == 0 && txtViaY0.getText().length() == 0) {
                getSelectedBB().sendData(250);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                getSelectedBB().sendData(250);
            }
            else {
                getSelectedBB().sendData(Integer.parseInt(txtViaX0.getText()));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                getSelectedBB().sendData(Integer.parseInt(txtViaY0.getText()));
            }
        });

        menuBar = new JMenuBar();
        menuBoeBot = new JMenu("BoeBot");
        menuFile = new JMenu("Bestand");

        menuBar.add(menuFile);
        menuBar.add(menuBoeBot);

        JMenuItem itemSettings = new JMenuItem("Settings");
        itemSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GridProperties();
            }
        });
        JMenuItem itemExit = new JMenuItem("Afsluiten");
        itemExit.addActionListener(e -> {
          System.exit(0);
        });
        menuFile.add(itemSettings);
        menuFile.add(itemExit);

        JMenuItem itemAddBB = new JMenuItem("Voeg BoeBot toe");
        itemAddBB.addActionListener(e -> newBoebotPanel = new NewBoebot());

        JMenuItem itemProperties = new JMenuItem("Eigenschappen");
        itemProperties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBoeBotPanel = new NewBoebot(MainPanel.this.getSelectedBB().getName(),
                                                MainPanel.this.getSelectedBB().getPort(),
                                                MainPanel.this.getSelectedBB().getHomex(),
                                                MainPanel.this.getSelectedBB().getHomey());
            }
        });

        JMenuItem itemDisconnectBB = new JMenuItem("Ontkoppel BoeBot");
        itemDisconnectBB.addActionListener((ActionEvent e) -> {
           BTConnection b = getSelectedBB();
           b.disconnect();
           System.out.println(b.getName() + " " + "Disconnected");
           Main.bots.remove(b);
           b = null;
           Main.mp.gridPanel.repaint();
           Main.mp.fillSelectedBBBox();
        });

        menuBoeBot.add(itemAddBB);
        menuBoeBot.add(itemProperties);
        menuBoeBot.add(itemDisconnectBB);

        setJMenuBar(menuBar);

        setContentPane(mainPanel);
        pack();
        setVisible(true);

        Thread t = new Thread(this, "t1");
        t.start();
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

    @Override
    public void run() {
        while(true) {
            gridPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Coordinate{
    int x;
    int y;

     Coordinate(int x, int y){
         this.x = x;
         this.y = y;
     }


}


