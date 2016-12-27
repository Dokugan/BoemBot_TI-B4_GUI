package gui;

import bluetooth.BTConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel {

    boolean drawSelected = false;
    private String inputToX = "";
    private String inputToY = "";
    private int posx = 0;
    private int posy = 0;
    int selectedX;
    int selectedY;
    private int vSpacing;
    private int hSpacing;
    private int height;
    private int width;
    int hLines = 9; //ammount of lines -1
    int vLines = 7; // ammount of lines -1

    public GridPanel() {
        super();

        setBackground(Color.WHITE);

        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                System.out.println(e.getX() + " " + e.getY());
                selectedX = checkMouseX(e.getX());
                selectedY = checkMouseY(e.getY());
                if(selectedX != -1 && selectedY != -1) {
                    inputToX = "";
                    inputToY = "";
                    inputToX += selectedX;
                    inputToY += selectedY;
                    drawSelected = true;
                    repaint();
                }

                Main.mp.txtToX.setText(inputToX);
                Main.mp.txtToY.setText(inputToY);
            }

            /**
            @param mouseX   The X coordinate of the mouse relative to the GridPanel.
            @return         X coordinatie of the grid coresponding to the X position of the mouse.
            */
            private int checkMouseX(int mouseX){
                posx = 0;

                for(int i = 0; i < vLines -1; i++)
                {
                    if((mouseX > (posx + hSpacing / 2 ) && (mouseX < posx + (hSpacing * 1.5)))){
                        return i;
                    }
                    posx += hSpacing;
                }
                return -1;
            }

            /**
             @param mouseY   The Y coordinate of the mouse relative to the GridPanel.
             @return         Y coordinatie of the grid coresponding to the Y position of the mouse.
             */
            private int checkMouseY(int mouseY){
                posy = 0;

                for(int i = 0; i < hLines -1; i++)
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

        posx = 0;
        posy = 0;

        width = getWidth();
        height = getHeight();

        hSpacing = width / vLines;
        vSpacing = height / hLines;

        //drawing horizontal lines
        for(int i = 1; i < hLines; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(posx, posy + (vSpacing * i), posx + width, posy + (vSpacing * i));
        }

        //drawing vertical lines
        for(int i = 1; i < vLines; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(posx + (hSpacing * i), posy, posx + (hSpacing * i), posy + height);
        }

        int markerSize = hSpacing / 8;

        //drawing selected position
        if(drawSelected){
                g.setColor(Color.BLUE);
                g.fillOval(hSpacing * (selectedX + 1) - markerSize / 2, vSpacing * (selectedY + 1) - markerSize / 2, markerSize, markerSize);


                g2d.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, hSpacing / 5));
                g2d.drawString(selectedX + "," + selectedY, hSpacing * (selectedX + 1) + markerSize / 2, vSpacing * (selectedY + 1) + (int) (1.5 * markerSize));
        }

        for(BTConnection b : Main.bots)
        {
            g.setColor(new Color(3, 79, 0));
            g.fillOval(hSpacing * (b.getCurrentPosx() +1) - markerSize / 2, vSpacing * (b.getCurrentPosy() + 1) - markerSize /2, markerSize,markerSize);

            g2d.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, hSpacing / 5));
            g2d.drawString(b.getName(), hSpacing * (b.getCurrentPosx() + 1) + markerSize / 2, vSpacing * (b.getCurrentPosy() + 1) + (int) (markerSize *1.5));
        }
    }
}