package gui;

import bluetooth.BTConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel {

    boolean drawSelectedTo = false;
    boolean drawSelectedVia = false;
    boolean btnToPressed = false;
    boolean btnViaPressed = false;
    private String inputToX = "";
    private String inputToY = "";
    private int posx = 0;
    private int posy = 0;
    int selectedToX;
    int selectedToY;
    int selectedViaX;
    int selectedViaY;
    private int vSpacing;
    private int hSpacing;
    private int height;
    private int width;
    int hLines = 9; //ammount of lines -1
    int vLines = 11; // ammount of lines -1

    public GridPanel() {
        super();

        setBackground(Color.WHITE);

        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (btnToPressed) {
                    selectedToX = checkMouseX(e.getX());
                    selectedToY = checkMouseY(e.getY());
                    if(selectedToX != -1 && selectedToY != -1) {
                        inputToX = "";
                        inputToY = "";
                        inputToX += selectedToX;
                        inputToY += selectedToY;
                        drawSelectedTo = true;
                        repaint();
                    }

                    Main.mp.txtToX.setText(inputToX);
                    Main.mp.txtToY.setText(inputToY);
                }

                if (btnViaPressed) {
                    selectedViaX = checkMouseX(e.getX());
                    selectedViaY = checkMouseY(e.getY());
                    if(selectedViaX != -1 && selectedViaY != -1) {
                        inputToX = "";
                        inputToY = "";
                        inputToX += selectedViaX;
                        inputToY += selectedViaY;
                        drawSelectedVia = true;
                        repaint();
                    }

                    Main.mp.txtViaX0.setText(inputToX);
                    Main.mp.txtViaY0.setText(inputToY);
                }
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

    public static Color getRandomColor(){
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        return new Color(r, g, b);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.setBackground(getRandomColor());
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        posx = 0;
        posy = 0;

        width = getWidth();
        height = getHeight();

        hSpacing = (int)Math.floor(width / vLines);
        vSpacing = (int)Math.floor(height / hLines);

        //drawing horizontal lines
        for(int i = 1; i < hLines; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(getRandomColor());
            g2d.drawLine(posx, posy + (vSpacing * i), posx + width, posy + (vSpacing * i));
        }

        //drawing vertical lines
        for(int i = 1; i < vLines; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(getRandomColor());
            g2d.drawLine(posx + (hSpacing * i), posy, posx + (hSpacing * i), posy + height);
        }

        int markerSize = hSpacing / 8;

        //drawing selected "to" position
        if(drawSelectedTo){
            g.setColor(Color.BLUE);
            g.fillOval(hSpacing * (selectedToX + 1) - markerSize / 2, vSpacing * (selectedToY + 1) - markerSize / 2, markerSize, markerSize);

            g2d.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, hSpacing / 5));
            g2d.drawString(selectedToX + "," + selectedToY, hSpacing * (selectedToX + 1) + markerSize / 2, vSpacing * (selectedToY + 1) + (int) (1.5 * markerSize));
            Main.mp.btnTo.setEnabled(true);
            btnToPressed = false;
        }

        //drawing selected via position
        if(drawSelectedVia){
            g.setColor(Color.BLUE);
            g.fillRect(hSpacing * (selectedViaX + 1) - markerSize / 2, vSpacing * (selectedViaY + 1) - markerSize / 2, markerSize, markerSize);

            g2d.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, hSpacing / 5));
            g2d.drawString(selectedViaX + "," + selectedViaY, hSpacing * (selectedViaX + 1) + markerSize / 2, vSpacing * (selectedViaY + 1) + (int) (1.5 * markerSize));
            Main.mp.btnVia1.setEnabled(true);
            btnViaPressed = false;
        }

        //drawing Boembots
        for(BTConnection b : Main.bots)
        {
            g.setColor(new Color(3, 80, 0));

            if(b.getDirection() == 3){
                int xPoints[] = {((b.getCurrentPosx() + 1) * hSpacing), ((b.getCurrentPosx() + 1) * hSpacing) + hSpacing / 10, ((b.getCurrentPosx() + 1) * hSpacing) - hSpacing / 10};
                int yPoints[] = {((b.getCurrentPosy() + 1) * vSpacing + vSpacing / 2) + vSpacing / 8, ((b.getCurrentPosy() + 1) * vSpacing + vSpacing / 2) - vSpacing / 8, ((b.getCurrentPosy() + 1) * vSpacing + vSpacing / 2) - vSpacing / 8};
                int n = 3;

                Polygon p = new Polygon(xPoints, yPoints, n);
                g.fillPolygon(p);
            }

            if(b.getDirection() == 1){
                int xPoints[] = {((b.getCurrentPosx() + 1) * hSpacing), ((b.getCurrentPosx() + 1) * hSpacing) + hSpacing / 10, ((b.getCurrentPosx() + 1) * hSpacing) - hSpacing / 10};
                int yPoints[] = {((b.getCurrentPosy() + 1) * vSpacing - vSpacing / 2) - vSpacing / 8, ((b.getCurrentPosy() + 1) * vSpacing - vSpacing / 2) + vSpacing / 8, ((b.getCurrentPosy() + 1) * vSpacing - vSpacing / 2) + vSpacing / 8};
                int n = 3;

                Polygon p = new Polygon(xPoints, yPoints, n);
                g.fillPolygon(p);
            }

            if(b.getDirection() == 4){
                int xPoints[] = {((b.getCurrentPosx() + 1) * hSpacing - hSpacing / 2) - hSpacing / 8, ((b.getCurrentPosx() + 1) * hSpacing - hSpacing / 2) + hSpacing / 8, ((b.getCurrentPosx() + 1) * hSpacing - hSpacing / 2) + hSpacing / 8};
                int yPoints[] = {((b.getCurrentPosy() + 1) * vSpacing), ((b.getCurrentPosy() + 1) * vSpacing) + vSpacing / 10, ((b.getCurrentPosy() + 1) * vSpacing) - vSpacing / 10};
                int n = 3;

                Polygon p = new Polygon(xPoints, yPoints, n);
                g.fillPolygon(p);
            }

            if(b.getDirection() == 2){
                int xPoints[] = {((b.getCurrentPosx() + 1) * hSpacing + hSpacing / 2) + hSpacing / 8, ((b.getCurrentPosx() + 1) * hSpacing + hSpacing / 2) - hSpacing / 8, ((b.getCurrentPosx() + 1) * hSpacing + hSpacing / 2) - hSpacing / 8};
                int yPoints[] = {((b.getCurrentPosy() + 1) * vSpacing), ((b.getCurrentPosy() + 1) * vSpacing) + vSpacing / 10, ((b.getCurrentPosy() + 1) * vSpacing) - vSpacing / 10};
                int n = 3;

                Polygon p = new Polygon(xPoints, yPoints, n);
                g.fillPolygon(p);
            }

//            g2d.fillRoundRect(hSpacing * (b.getCurrentPosx() +1) - markerSize / 2, vSpacing * (b.getCurrentPosy() + 1) - markerSize /2, markerSize,markerSize,5,5);
//
            g2d.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, hSpacing / 5));
            g2d.drawString(b.getName(), hSpacing * (b.getCurrentPosx() + 1) + markerSize / 2, vSpacing * (b.getCurrentPosy() + 1) + (int) (markerSize *1.5));
        }
    }
}