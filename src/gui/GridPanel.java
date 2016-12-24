package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel {

    private int posx = 0;
    private int posy = 0;
    private int vSpacing;
    private int hSpacing;
    private int height;
    private int width;
    int hLines = 9; //ammount of lines -1
    int vLines = 7; // ammount of lines -1

    public GridPanel() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setBackground(Color.WHITE);

        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                String inputTo = "";
                inputTo = inputTo + checkMouseX(e.getX());
                inputTo = inputTo + " , ";
                inputTo = inputTo + checkMouseY(e.getY());

                Main.mp.txtTo.setText(inputTo);
            }

            private int checkMouseX(int mouseX){
                posx = 0;

                for(int i = 0; i < hLines; i++)
                {
                    if((mouseX > (posx + hSpacing / 2 ) && (mouseX < posx + (hSpacing * 1.5)))){
                        return i;
                    }
                    posx += hSpacing;
                }
                return -1;
            }

            private int checkMouseY(int mouseY){
                posy = 0;

                for(int i = 0; i < vLines; i++)
                {
                    if((mouseY > (posy + vSpacing / 2 ) && (mouseY < posy + (vSpacing * 1.5)))){
                        return i;
                    }
                    posy += vSpacing;
                }
                return -1;
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        width = getWidth();
        height = getHeight();

        hSpacing = width / vLines;
        vSpacing = height / hLines;

        //drawing horizontal lines
        for(int i = 0; i < hLines; i++){
            g2d.drawLine(posx, posy + (vSpacing * i), posx + width, posy + (vSpacing * i));

        }

        //drawing vertical lines
        for(int i = 0; i < vLines; i++){
            g2d.drawLine(posx + (hSpacing * i), posy, posx + (hSpacing * i), posy + height);

        }
    }
}