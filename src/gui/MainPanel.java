package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JFrame{


    public MainPanel()
    {
        super("testing shit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1280, 720);

        class ContentPanel extends JPanel
        {

            int posx;
            int posy;

            String mouseButton = "nothing";

            private ContentPanel() {
                setBackground(Color.WHITE);

                JButton b = new JButton("kaas");
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mouseButton = "test";
                        repaint();
                        b.setFocusable(false);
                    }
                });
                b.setBounds(50,50,50,50);
                add(b);

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        mouseButton = Integer.toString(e.getButton());
                        repaint();
                    }
                });

                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        System.out.println(e.getKeyChar());
                        moveRect(e.getKeyChar());
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

                this.setFocusable(true);
                this.requestFocusInWindow();

            }

            private void moveRect(char key)
            {
                if(key == 'd'){posx = posx + 10;}
                System.out.println(posx);
                repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(posx, posy, 50, 50);
                g.drawString(mouseButton, 10, 10);
            }
        }




        setContentPane(new ContentPanel());
        setVisible(true);
    }
}