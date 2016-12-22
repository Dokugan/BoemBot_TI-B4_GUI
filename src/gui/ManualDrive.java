package gui;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ManualDrive extends JFrame{
    private JPanel mainPanel;
    private JButton btnRight;
    private JButton btnForward;
    private JButton btnLeft;
    private JButton btnBack;

    private boolean breakUpdate = false;

    private final Set<Integer> pressed = new HashSet<Integer>();

    public ManualDrive() {
        super("Handmatig besturen");

        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                breakUpdate = true;
            }
        };

        addWindowListener(exitListener);

        setResizable(false);
        setContentPane(mainPanel);
        pack();
        setVisible(true);

        if( isFocusable()){setFocusable(true);}
        setFocusTraversalKeysEnabled(false);

        btnForward.setFocusable(false);
        btnLeft.setFocusable(false);
        btnRight.setFocusable(false);
        btnBack.setFocusable(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(pressed.size() < 2)
                    pressed.add(e.getKeyCode());

                switch(e.getKeyCode())
                {
                    case 87: btnForward.getModel().setPressed(true);
                        break;
                    case 65: btnLeft.getModel().setPressed(true);
                        break;
                    case 83: btnBack.getModel().setPressed(true);
                        break;
                    case 68: btnRight.getModel().setPressed(true);
                }

                for(Integer i:pressed){
                    System.out.println(i);

                }
                System.out.println("-------------------------");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressed.remove(e.getKeyCode());
                switch(e.getKeyCode())
                {
                    case 87: btnForward.getModel().setPressed(false);
                        break;
                    case 65: btnLeft.getModel().setPressed(false);
                        break;
                    case 83: btnBack.getModel().setPressed(false);
                        break;
                    case 68: btnRight.getModel().setPressed(false);
                }
            }
        });

        update();
    }

    private void update()
    {
        while (!breakUpdate){

            if(pressed.size() == 1)
            {
                if(pressed.contains(KeyEvent.VK_S))
                    Main.conn.sendData(0);
                if(pressed.contains(KeyEvent.VK_D))
                    Main.conn.sendData(2);
                if(pressed.contains(KeyEvent.VK_W))
                    Main.conn.sendData(4);
                if(pressed.contains(KeyEvent.VK_A))
                    Main.conn.sendData(6);
                if(pressed.contains(KeyEvent.VK_8))
                    Main.conn.sendData(10);
                if(pressed.contains(KeyEvent.VK_NUMPAD8))
                    Main.conn.sendData(12);
            }
            else if(pressed.size() == 2)
            {
                if(pressed.contains(KeyEvent.VK_S) && pressed.contains(KeyEvent.VK_A))
                    Main.conn.sendData(1);
                if(pressed.contains(KeyEvent.VK_A) && pressed.contains(KeyEvent.VK_W))
                    Main.conn.sendData(3);
                if(pressed.contains(KeyEvent.VK_W) && pressed.contains(KeyEvent.VK_D))
                    Main.conn.sendData(5);
                if(pressed.contains(KeyEvent.VK_D) && pressed.contains(KeyEvent.VK_S))
                    Main.conn.sendData(7);
            }
            else Main.conn.sendData(255);
        }
    }
}
