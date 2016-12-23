package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class GridPanel extends JPanel {

    private int posx = 0;
    private int posy = 0;
    private int vSpacing;
    private int hSpacing;
    private int height;
    private int width;
    int hLines = 6; //ammount of lines -1
    int vLines = 6; // ammount of lines -1

    public GridPanel() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
